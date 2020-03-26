package com.hao.movieshareback.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.hao.movieshareback.dao.*;
import com.hao.movieshareback.model.*;
import com.hao.movieshareback.vo.*;
import com.hao.movieshareback.vo.auth.UserVo;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.ml.job.results.Bucket;
import org.elasticsearch.common.lucene.search.function.FieldValueFactorFunction;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.index.query.functionscore.FieldValueFactorFunctionBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.*;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

@Service
public class VideoService {
    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private PictureMapper pictureMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private TagService tagService;

    @Resource(name = "highLevelElasticsearchClient")
    private RestHighLevelClient highLevelClient;

    public VideoDetailVo getVideoDetail(Integer videoId){
        Video video = videoMapper.getVideo(videoId);
        if (video==null){
            return null;
        }
        return toVideoDetailVo(video);
    }

    public XPage<VideoDetailVo> getVideoDetailByCondition(Video condition,Integer pageNum,Integer pageSize){
        Page page = new Page(pageNum,pageSize);
        page.setOrderColumn(Collections.singletonList("created_time"));
        page.setOrderType("DESC");
        PageList<Video> videos = videoMapper.getVideoDetailListLikeName(page,condition);
        PageList<VideoDetailVo> videoDetailVos = new PageList<>();
        videoDetailVos.setPageInfo(videos.getPageInfo());
        videos.forEach(video ->{
                videoDetailVos.add(toVideoDetailVo(video));
        });
        return XPage.wrap(videoDetailVos);
    }

    public void incrementVideoPlayCount(Integer videoId){
        videoMapper.incrementVideoPlayCount(videoId);
    }

    public XPage<VideoDetailVo> getFavoriteVideo(Video condition,Integer userId,Integer pageNum,Integer pageSize){
        Page page = new Page(pageNum,pageSize);
        PageList<Video> videos = videoMapper.getFavoriteVideoDetailList(page,condition,userId);
        PageList<VideoDetailVo> videoDetailVos = new PageList<>();
        videoDetailVos.setPageInfo(videos.getPageInfo());
        videos.forEach(video ->{
            videoDetailVos.add(toVideoDetailVo(video));
        });
        return XPage.wrap(videoDetailVos);
    }

    public XPage<VideoDetailVo> getVideoByCategory(String orderField,String categoryName,Integer pageNum,Integer pageSize) throws IOException {
        Category category = categoryMapper.selectCategoryByName(categoryName);
        Map<String,Object> resultMap = searchVideo(orderField,null,null,
                category.getCategoryId(),null,null,pageNum,pageSize);
        return (XPage<VideoDetailVo>) resultMap.get("videoList");
    }

    public Map<String,Object> searchVideo(String orderField, String searchKey, String tagName,
                                             Integer categoryId, String startDate, String endDate, Integer pageNum, Integer pageSize) throws IOException {
        String order="default";
        switch (orderField){
            case "default":order="_score";break;
            case "rate":order="videoRate";break;
            case "playCount":order="videoPlayCount";break;
            case "time":order="createdTime";break;
            default:throw new RuntimeException("UnKown orderType");
        }
        Integer from=(pageNum-1)*pageSize,to=pageSize;
        SearchSourceBuilder searchSourceBuilder = createSearchRequest(order, searchKey, tagName,
                categoryId, startDate, endDate, from, to);
        System.out.println(searchSourceBuilder);
        SearchRequest searchRequest = new SearchRequest("video").source(searchSourceBuilder);
        SearchResponse searchResponse= highLevelClient.search(searchRequest,RequestOptions.DEFAULT);
        Map<Long,String> aggregMap= aggregToMap(searchResponse.getAggregations().getAsMap());
        Long totalHit= searchResponse.getHits().getTotalHits().value;
        SearchHit[] searchHits= searchResponse.getHits().getHits();

        List<VideoDetailVo> videoDetailVos = new ArrayList<>(searchHits.length);
        if (searchHits!=null&&searchHits.length > 0){
            for (SearchHit searchHit : searchHits) {
                VideoDetailVo videoDetailVo = getVideoDetail(Integer.parseInt(searchHit.getId()));
                if (searchHit.getHighlightFields() != null) {
                    HighlightField highlightField = searchHit.getHighlightFields().get("videoTitle");
                    if (highlightField != null && highlightField.getFragments().length > 0) {
                        String highLightTitle = highlightField.getFragments()[0].string();
                        videoDetailVo.setTitle(highLightTitle);
                    }
                }
                videoDetailVos.add(videoDetailVo);
            }
        }
        PageInfo pageInfo = new PageInfo();
        pageInfo.setTotal(totalHit);
        Map<String ,Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put("videoList",XPage.newInstance(videoDetailVos,pageInfo));
        stringObjectMap.put("aggregateMap",aggregMap);
        return stringObjectMap;
    }

    private Map<Long,String> aggregToMap(Map<String,Aggregation> aggregationMap){
        TreeMap<Long,String> aggMap=Maps.newTreeMap();
        ParsedStringTerms tagBucket = (ParsedStringTerms) aggregationMap.get("group_by_tags");
        List<ParsedStringTerms.ParsedBucket> bucketList = (List<ParsedStringTerms.ParsedBucket>) tagBucket.getBuckets();
        bucketList.forEach((item)->{
            aggMap.put(item.getDocCount(),item.getKeyAsString());
        });
        return aggMap;
    }

    private SearchSourceBuilder createSearchRequest(String orderField, String searchKey, String tagName,
                                              Integer categoryId, String startDate,String endDate,Integer from, Integer size){
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (searchKey!=null){
            MultiMatchQueryBuilder multiMatchQueryBuilder= QueryBuilders.multiMatchQuery(searchKey).
                    field("video_title",3).field("uploadUserName",2).field("videoDesc",1).
                    field("tags",2).field("categoryName",2);
            boolQueryBuilder.must(multiMatchQueryBuilder);

        }
        if (tagName!=null){
            TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("tags",tagName);
            boolQueryBuilder.must(termQueryBuilder);
        }

        if (categoryId!=null){
            TermQueryBuilder categoryTerm = QueryBuilders.termQuery("categoryId",categoryId);
            boolQueryBuilder.must(categoryTerm);
        }

        if (startDate!=null&&endDate!=null){
            RangeQueryBuilder dateRange=QueryBuilders.rangeQuery("createdTime").lte(startDate).gte(endDate);
            boolQueryBuilder.must(dateRange);
        }

        FieldValueFactorFunctionBuilder videoPlayCount= ScoreFunctionBuilders.fieldValueFactorFunction("videoPlayCount")
                .factor(3).modifier(FieldValueFactorFunction.Modifier.LOG2P);
        FieldValueFactorFunctionBuilder videoRate=ScoreFunctionBuilders.fieldValueFactorFunction("videoRate").factor(5)
                .modifier(FieldValueFactorFunction.Modifier.LOG2P);
        FieldValueFactorFunctionBuilder videoCommentCount=ScoreFunctionBuilders.fieldValueFactorFunction("videoCommentPerson").factor(2)
                .modifier(FieldValueFactorFunction.Modifier.LOG2P);


        FunctionScoreQueryBuilder functionScoreQuery = QueryBuilders.functionScoreQuery(boolQueryBuilder,new FunctionScoreQueryBuilder.FilterFunctionBuilder[]{
                new FunctionScoreQueryBuilder.FilterFunctionBuilder(videoPlayCount),
                new FunctionScoreQueryBuilder.FilterFunctionBuilder(videoRate),
                new FunctionScoreQueryBuilder.FilterFunctionBuilder(videoCommentCount)
        });

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().query(functionScoreQuery).
                sort(SortBuilders.fieldSort(orderField).order(SortOrder.DESC)).from(from).size(size);
        searchSourceBuilder.aggregation(AggregationBuilders.terms("group_by_tags").field("tags"))
                .highlighter(new HighlightBuilder().field("videoTitle"));
        return searchSourceBuilder;
    }

    private VideoDetailVo toVideoDetailVo(Video video){
        UserVo userVo = userService.getUserVoByUserId(video.getUploadUserId());
        Picture picture= pictureMapper.selectPictureById(video.getVideoPosterId());
        Category category = categoryMapper.selectCategoryById(video.getCategoryId());
        List<Tag> tagList = tagService.getTagListByVideoId(video.getVideoId());
        return new VideoDetailVo(video.getVideoId(),video.getVideoTitle(),category,video.getCreatedTime(),video.getVideoDesc(),tagList,userVo,video.getVideoPlayCount(),
                video.getVideoCommentPerson(),picture,video.getVideoRate());
    }

    public void indexVideo(List<VideoIndexVo> videoIndexVoList) throws IOException {
        if (videoIndexVoList==null||videoIndexVoList.size()==0){
            return;
        }
        ObjectMapper mapper = new ObjectMapper();
        BulkRequest bulkRequest = new BulkRequest();
        for (VideoIndexVo videoIndexVo:videoIndexVoList){
            String videoIndexJson = mapper.writeValueAsString(videoIndexVo);
            IndexRequest indexRequest = new IndexRequest("video");
            indexRequest.id(videoIndexVo.getVideoId().toString());
            System.out.println(videoIndexJson);
            indexRequest.source(videoIndexJson,XContentType.JSON);
            bulkRequest.add(indexRequest);
        }
        BulkResponse bulkItemResponses = highLevelClient.bulk(bulkRequest,RequestOptions.DEFAULT);
        System.out.println(mapper.writeValueAsString(bulkItemResponses));
    }
}
