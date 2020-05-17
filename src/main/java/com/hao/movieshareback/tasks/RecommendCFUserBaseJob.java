package com.hao.movieshareback.tasks;

import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.hao.movieshareback.config.SystemConst;
import com.hao.movieshareback.dao.LogMapper;
import com.hao.movieshareback.dao.RateVideoCommentMapper;
import com.hao.movieshareback.dao.VideoMapper;
import com.hao.movieshareback.model.FavoriteVideo;
import com.hao.movieshareback.model.Log;
import com.hao.movieshareback.model.RateVideoComment;

import com.hao.movieshareback.model.bo.VisitedRate;
import com.hao.movieshareback.recommend.RecommendByCF;
import com.hao.movieshareback.recommend.RecommendByVideoSimilary;
import com.hao.movieshareback.service.FavoriteVideoService;
import com.hao.movieshareback.utils.SpringContextJobUtil;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.*;

public class RecommendCFUserBaseJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        RecommendByCF recommendByCF = SpringContextJobUtil.getBean(RecommendByCF.class);
        LogMapper logMapper = SpringContextJobUtil.getBean(LogMapper.class);
        RateVideoCommentMapper videoCommentMapper = SpringContextJobUtil.getBean(RateVideoCommentMapper.class);
        FavoriteVideoService favoriteVideoService = SpringContextJobUtil.getBean(FavoriteVideoService.class);
        RecommendByVideoSimilary recommendByVideoSimilary = SpringContextJobUtil.getBean(RecommendByVideoSimilary.class);
        VideoMapper videoMapper = SpringContextJobUtil.getBean(VideoMapper.class);

        Date endDate =jobExecutionContext.getFireTime();
        Date startDate = DateUtil.offsetDay(endDate,-100);
        Map<String,Object> params = new HashMap<>();
        params.put("play_video",1.0);
        params.put("comment_video",2.0);

        List<Log> logList=logMapper.selectLogForRecommendCF(DateUtil.formatDateTime(startDate),DateUtil.formatDateTime(endDate));
        Map<Integer,List<Log>> logForUser = new HashMap();
        Map<Integer,List<VisitedRate>> visitedRateMap = new HashMap<>();
        //以userid为key聚合日志
        for (Log log:logList){
            if (!log.getUserId().equals(-1)){
                logForUser.compute(log.getUserId(),(key,oldval)->{
                    List<Log> logs=null;
                    if (oldval==null){
                        logs=new LinkedList<>();
                    }else {
                        logs=oldval;
                    }
                    logs.add(log);
                    return logs;
                });
            }
        }

        ObjectMapper objectMapper = new ObjectMapper();
        // 以用户为单位分析日志
        for (Integer userId:logForUser.keySet()){
            List<Log> visitedLogs = logForUser.get(userId);
            HashMap<Integer,Double> videoScoreMap = Maps.newHashMap();
            for (Log visitedLog:visitedLogs){
                Integer videoId = -1;
                JsonNode paramsJsonNode= null;
                try {
                    paramsJsonNode = objectMapper.readTree(visitedLog.getParams());
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                switch (visitedLog.getBusinessType()){
                    case "play_video":videoId=paramsJsonNode.get("videoId").asInt();break;
                    case "comment_video":videoId=paramsJsonNode.get("videoComment").get("videoId").asInt();break;
                }
                //忽略videoId为-1的日志
                if (videoId==-1||videoMapper.getVideo(videoId)==null){
                    continue;
                }
                Double score = params.get(visitedLog.getBusinessType())==null?1.0:(Double) params.get(visitedLog.getBusinessType())*1.0;
                videoScoreMap.compute(videoId,(key,value)->{
                    if (value==null){
                        return score;
                    }else {
                        return score+value;
                    }
                });
            }

            for (Integer videoId:videoScoreMap.keySet()){
                boolean isFavorite = favoriteVideoService.isFavorite(new FavoriteVideo(null,videoId,userId));
                if (isFavorite){
                    videoScoreMap.put(videoId,videoScoreMap.get(videoId)+10);
                }
            }



            for (Integer videoIdInMap:videoScoreMap.keySet()){
                visitedRateMap.compute(userId,(k,oldVal)->{
                    List<VisitedRate> rateList = null;
                    if (oldVal==null){
                        rateList = new LinkedList<>();
                    }else {
                        rateList = oldVal;
                    }
                    // 归一化以5星为标准
                    rateList.add(new VisitedRate(userId,videoIdInMap,videoScoreMap.get(videoIdInMap)/2));
                    return rateList;
                });
            }
        }

        //以用户为单位聚合
        List<RateVideoComment> rateVideoCommentList = videoCommentMapper.selectAllRateComment();
        Map<Integer,List<VisitedRate>> rateVideoCommentMap = new HashMap<>();

        for (RateVideoComment rateComment:rateVideoCommentList){
            rateVideoCommentMap.compute(rateComment.getCommentUserId(),(k,oldVal)->{
                List<VisitedRate> visitedRateList=null;
                if (oldVal==null){
                    visitedRateList = new LinkedList<>();
                }else {
                    visitedRateList =oldVal;
                }
                visitedRateList.add(new VisitedRate(k,rateComment.getVideoId(),rateComment.getRate()));
                return visitedRateList;
            });
        }

        //将用户点评和浏览记录推测评分进行融合

        Map<Integer,List<VisitedRate>> mergeUserMap = new HashMap<>();

        for (Integer userId:visitedRateMap.keySet()){
            List<VisitedRate> visitedRateList = visitedRateMap.get(userId);
            List<VisitedRate> commentRateList = rateVideoCommentMap.remove(userId);
            HashMap<Integer, VisitedRate>  mergeMap = new HashMap<>();
            if (commentRateList!=null) {
                for (VisitedRate commentRate : commentRateList) {
                    mergeMap.put(commentRate.getVideoId(), commentRate);
                }
            }
            if (visitedRateList!=null) {
                for (VisitedRate visitedRate : visitedRateList) {
                    if (!mergeMap.containsKey(visitedRate.getVideoId())) {
                        mergeMap.put(visitedRate.getVideoId(), visitedRate);
                    }else {
                        mergeMap.put(visitedRate.getVideoId(),new VisitedRate(visitedRate.getUserId(),visitedRate.getVideoId(),
                                visitedRate.getRate()+mergeMap.get(visitedRate.getVideoId()).getRate()));
                    }
                }
            }
            mergeUserMap.put(userId,new ArrayList<>(mergeMap.values()));
        }

        mergeUserMap.putAll(rateVideoCommentMap);
        Map<Integer,List<RecommendedItem>> cfRecommendResult = null;
        try {
            cfRecommendResult = recommendByCF.recommend(100,100,mergeUserMap);
        } catch (TasteException e) {
            e.printStackTrace();
        }
        recommendByVideoSimilary.recommend(mergeUserMap);
    }
}
