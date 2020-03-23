package com.hao.movieshareback.service;

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
        Integer start = (pageNum-1)*pageSize;
        Integer end = pageNum*pageSize-1;

        Set<String> hotVideos= redisTemplate.opsForZSet().reverseRange("RECOMMEND_BY_STATISTICS",start,end);
        List<VideoDetailVo> recommendList= new ArrayList<>(pageSize);


        for (String videoCategoryPair:hotVideos){
            Integer videoId = Integer.valueOf(videoCategoryPair.split(",")[0]);
            recommendList.add(videoService.getVideoDetail(videoId));
        }

        PageInfo pageInfo = new PageInfo();
        pageInfo.setTotal((long)recommendList.size());

        return XPage.newInstance(recommendList,pageInfo);
    }

    public List<CategoryRecommendVo> getRecentlyHotVideoCategory(Integer pageNum,Integer pageSize){
        Integer start=(pageNum-1)*pageSize;
        Integer end=pageNum*pageSize-1;
        List<Category> categoryList = categoryService.listCategory();
        List<CategoryRecommendVo> categoryRecommendVoList = new LinkedList<>();
        categoryList.forEach(category -> {
            String cacheKey = "CATEGORY_RECOMMEND"+"_"+category.getCategoryId();
            Set<Integer> recomendVideoSet = redisTemplate.opsForZSet().reverseRange(cacheKey,start,end);
            if (recomendVideoSet==null||recomendVideoSet.isEmpty()){
                return;
            }
            PageInfo pageInfo = new PageInfo();
            pageInfo.setTotal((long) recomendVideoSet.size());
            List<VideoDetailVo> videoDetailVos = new ArrayList<>(recomendVideoSet.size());
            recomendVideoSet.forEach(videoId->{
                videoDetailVos.add(videoService.getVideoDetail(videoId));
            });
            categoryRecommendVoList.add(new CategoryRecommendVo(category,XPage.newInstance(videoDetailVos,pageInfo)));
        });
        return categoryRecommendVoList;
    }

    public XPage<VideoDetailVo> getPersonalRecommendVideoList(Integer pageNum,Integer pageSize){
        Integer start = (pageNum-1)*pageSize;
        Integer end = pageNum*pageSize-1;

        JwtUser user = (JwtUser) SecurityUtils.getUserDetails();
        String cacheKey="RECOMMEND_BY_USER_BASE_CF_";
        Set<Integer> videoSet = redisTemplate.opsForZSet().reverseRange(cacheKey+user.getUserId(),start,end);
        if (videoSet==null){
            return XPage.wrap(new PageList<>());
        }

        List<VideoDetailVo> videoDetailVoList = new LinkedList<>();

        PageInfo pageInfo = new PageInfo();
        pageInfo.setTotal((long)videoSet.size());
        videoSet.forEach(videoId->{
            videoDetailVoList.add(videoService.getVideoDetail(videoId));
        });
        return XPage.newInstance(videoDetailVoList,pageInfo);
    }

}
