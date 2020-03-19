package com.hao.movieshareback.controller;

import com.hao.movieshareback.annotation.auth.AnonymousAccess;
import com.hao.movieshareback.service.VideoService;
import com.hao.movieshareback.service.task.TaskManager;
import com.hao.movieshareback.vo.ResultBody;
import com.hao.movieshareback.vo.VideoDetailVo;
import com.hao.movieshareback.vo.XPage;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.lucene.search.function.FieldValueFactorFunction;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FieldValueFactorFunctionBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

@RestController
public class TestController {
    @Resource(name = "highLevelElasticsearchClient")
    private RestHighLevelClient highLevelClient;


    @Autowired
    VideoService videoService;


    @Autowired
    private TaskManager taskManager;

    @AnonymousAccess
    @GetMapping("/test")
    public ResultBody testSearch() throws IOException {
        MultiMatchQueryBuilder multiMatchQueryBuilder= QueryBuilders.multiMatchQuery("测试").field("video_title");

        FieldValueFactorFunctionBuilder videoPlayCount= ScoreFunctionBuilders.fieldValueFactorFunction("video_play_count")
                .factor(3).modifier(FieldValueFactorFunction.Modifier.LOG2P);

        FieldValueFactorFunctionBuilder videoRate=ScoreFunctionBuilders.fieldValueFactorFunction("video_rate").factor(5)
                .modifier(FieldValueFactorFunction.Modifier.LOG2P);


        FunctionScoreQueryBuilder functionScoreQuery = QueryBuilders.functionScoreQuery(multiMatchQueryBuilder,new FunctionScoreQueryBuilder.FilterFunctionBuilder[]{
                new FunctionScoreQueryBuilder.FilterFunctionBuilder(videoPlayCount),
                new FunctionScoreQueryBuilder.FilterFunctionBuilder(videoRate)
        });
        SearchRequest searchRequest = new SearchRequest("video");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().query(functionScoreQuery).sort(SortBuilders.scoreSort()).from(2).size(1);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse=highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(searchSourceBuilder);
        System.out.println(searchResponse);
        return ResultBody.success(searchResponse);
    }


    @AnonymousAccess
    @GetMapping("/test2")
    public Map<String, Object> test1(String orderField, String searchKey, String tagName,
                                     Integer categoryId, String startDate, String endDate, Integer pageNum, Integer pageSize){
        try {
            return videoService.searchVideo(orderField, searchKey, tagName, categoryId, startDate, endDate, pageNum, pageSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @AnonymousAccess()
    @GetMapping("testjob")
    public ResultBody createTask(){
        taskManager.removeJob("test","test","test",
                "tste");
        return ResultBody.success();
    }
}
