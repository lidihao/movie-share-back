package com.hao.movieshareback.service;

import com.hao.movieshareback.config.SystemConst;
import com.hao.movieshareback.model.Category;
import com.hao.movieshareback.utils.SecurityUtils;
import com.hao.movieshareback.vo.*;
import com.hao.movieshareback.vo.auth.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
public class RecommendService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private VideoService videoService;

    @Autowired
    private CategoryService categoryService;


    public XPage<VideoDetailVo> getRecentlyHotVideo(Integer pageNum,Integer pageSize){
        List<VideoDetailVo> recommendList= new ArrayList<>(pageSize);
        Integer startPageNum=pageNum;
        List<String> removeVideoId=new LinkedList<>();

        while (recommendList.size()<pageSize){
            Integer start = (startPageNum-1)*pageSize;
            Integer end = startPageNum*pageSize-1;
            Set<String> hotVideos= redisTemplate.opsForZSet().reverseRange("RECOMMEND_BY_STATISTICS",start,end);
            if (hotVideos==null||hotVideos.isEmpty()){
                break;
            }

            for (String videoCategoryPair:hotVideos){
                Integer videoId = Integer.valueOf(videoCategoryPair.split(",")[0]);
                VideoDetailVo videoDetailVo = videoService.getVideoDetail(videoId);
                if (recommendList.size()==pageSize){
                    break;
                }
                if (videoDetailVo != null) {
                    recommendList.add(videoDetailVo);
                }else {
                    //移除不存在的VideoId
                    removeVideoId.add(videoCategoryPair);
                }
            }
            startPageNum++;
        }
        if (!removeVideoId.isEmpty()) {
            redisTemplate.opsForZSet().remove("RECOMMEND_BY_STATISTICS", removeVideoId.toArray());
        }


        PageInfo pageInfo = new PageInfo();
        pageInfo.setTotal(redisTemplate.opsForZSet().size("RECOMMEND_BY_STATISTICS"));

        return XPage.newInstance(recommendList,pageInfo);
    }

    public List<CategoryRecommendVo> getRecentlyHotVideoCategory(Integer pageNum,Integer pageSize){
        List<Category> categoryList = categoryService.listCategory();
        List<CategoryRecommendVo> categoryRecommendVoList = new LinkedList<>();
        categoryList.forEach(category -> {
            Integer startPageNum=pageNum;
            String cacheKey = "CATEGORY_RECOMMEND"+"_"+category.getCategoryId();
            List<VideoDetailVo> videoDetailVos = new ArrayList<>(pageSize);
            List<Integer> removeVideoId=new LinkedList<>();
            while (videoDetailVos.size()<pageSize){
                Integer start=(startPageNum-1)*pageSize;
                Integer end=startPageNum*pageSize-1;
                Set<Integer> recomendVideoSet = redisTemplate.opsForZSet().reverseRange(cacheKey,start,end);
                if (recomendVideoSet==null||recomendVideoSet.isEmpty()){
                    break;
                }
                for(Integer videoId:recomendVideoSet)
                {
                    VideoDetailVo videoDetailVo =videoService.getVideoDetail(videoId);
                    if (videoDetailVos.size()==pageSize){
                        break;
                    }
                    if (videoDetailVo!=null) {
                        videoDetailVos.add(videoDetailVo);
                    }else {
                        //移除不存在的VideoId
                        removeVideoId.add(videoId);
                    }
                }
                startPageNum++;
            }
            if (!removeVideoId.isEmpty()) {
                redisTemplate.opsForZSet().remove(cacheKey, removeVideoId.toArray());
            }

            PageInfo pageInfo = new PageInfo();
            pageInfo.setTotal(redisTemplate.opsForZSet().size(cacheKey));
            categoryRecommendVoList.add(new CategoryRecommendVo(category,XPage.newInstance(videoDetailVos,pageInfo)));
        });
        return categoryRecommendVoList;
    }

    public XPage<VideoDetailVo> getPersonalRecommendVideoList(Integer pageNum,Integer pageSize){
        Integer startPageNum=pageNum;
        JwtUser user = (JwtUser) SecurityUtils.getUserDetails();
        String cacheKey = "RECOMMEND_BY_USER_BASE_CF_";
        List<VideoDetailVo> videoDetailVoList = new LinkedList<>();
        List<Integer> removeVideoId=new LinkedList<>();

        while (videoDetailVoList.size()<pageSize) {
            Integer start = (startPageNum-1)*pageSize;
            Integer end = startPageNum*pageSize-1;
            Set<Integer> videoSet = redisTemplate.opsForZSet().reverseRange(cacheKey + user.getUserId(), start, end);
            if (videoSet == null||videoSet.size()==0) {
                break;
            }

            for (Integer videoId:videoSet){
                VideoDetailVo videoDetailVo = videoService.getVideoDetail(videoId);
                if (videoDetailVoList.size()==pageSize){
                    break;
                }
                if (videoDetailVo != null) {
                    videoDetailVoList.add(videoDetailVo);
                }else {
                    //移除不存在的VideoId
                    removeVideoId.add(videoId);
                }
            }
            startPageNum++;
        }
        if (!removeVideoId.isEmpty()) {
            redisTemplate.opsForZSet().remove(cacheKey + user.getUserId(), removeVideoId.toArray());
        }
        PageInfo pageInfo = new PageInfo();
        pageInfo.setTotal(redisTemplate.opsForZSet().size(cacheKey + user.getUserId()));
        return XPage.newInstance(videoDetailVoList,pageInfo);
    }

    public XPage<VideoDetailVo> getSimilarVideoList(Integer videoId,Integer pageNum,Integer pageSize){
        Integer start = (pageNum-1)*pageSize;
        Integer end = pageNum*pageSize-1;
        String cacheKey= SystemConst.VIDEO_SIMILARITY_CACHE_PREFIX;
        Set<Integer> videoSet = redisTemplate.opsForZSet().reverseRange(cacheKey+videoId,start,end);
        if (videoSet==null){
            return XPage.wrap(new PageList<>());
        }
        List<VideoDetailVo> videoDetailVoList = new LinkedList<>();

        PageInfo pageInfo = new PageInfo();
        pageInfo.setTotal((long)videoSet.size());
        videoSet.forEach(video->{
            VideoDetailVo videoDetailVo =videoService.getVideoDetail(video);
            if (videoDetailVo!=null) {
                videoDetailVoList.add(videoDetailVo);
            }else {
                redisTemplate.opsForZSet().remove(cacheKey+videoId,video);
            }
        });
        return XPage.newInstance(videoDetailVoList,pageInfo);
    }
}
