package com.hao.movieshareback.recommend;

import cn.hutool.core.collection.CollUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.hao.movieshareback.dao.LogMapper;
import com.hao.movieshareback.dao.VideoMapper;
import com.hao.movieshareback.model.Log;
import com.hao.movieshareback.model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class RecommendByStatistics {
    @Autowired
    private LogMapper logMapper;

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private RedisTemplate redisTemplate;



    public void recommend(String startDate, String endDate, Map<String,Double> weights) throws JsonProcessingException {
        List<Log> logList = logMapper.selectLogForRecommend(startDate,endDate);
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<Integer,Double> videoScoreMap = Maps.newHashMap();
        for (Log log:logList){
            String businessType = log.getBusinessType();
            JsonNode paramsJsonNode=objectMapper.readTree(log.getParams());
            Integer videoId=-1;
            switch (businessType){
                case "play_video":videoId=paramsJsonNode.get("videoId").asInt();break;
                case "favorite_video":videoId=paramsJsonNode.get("favoriteVideo").get("videoId").asInt();break;
                case "comment_video":videoId=paramsJsonNode.get("videoComment").get("videoId").asInt();break;
            }
            Double score = weights.get(businessType)==null?1.0:weights.get(businessType)*1.0;
            videoScoreMap.compute(videoId,(key,value)->{
                if (value==null){
                    return score;
                }else {
                    return score+value;
                }
            });
        }

        videoScoreMap.remove(-1);
        String cacheKey="RECOMMEND_BY_STATISTICS";
        String categoryCacheKey="CATEGORY_RECOMMEND";
        for (Integer videoId:videoScoreMap.keySet()){
            Video video = videoMapper.getVideo(videoId);
            double totalScore=weights.get("log_weight")*videoScoreMap.get(videoId)+
                    weights.get("rate_weight")*videoScoreMap.get(videoId);
            Integer categoryId = video.getCategoryId();
            String value = videoId+","+categoryId;
            redisTemplate.opsForZSet().add(cacheKey,value,totalScore);
            Set set =redisTemplate.opsForZSet().range(cacheKey,0,-1);
            redisTemplate.opsForZSet().add(categoryCacheKey+"_"+categoryId,videoId,totalScore);
        }

    }
}
