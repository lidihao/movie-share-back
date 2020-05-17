package com.hao.movieshareback.recommend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.hao.movieshareback.config.SystemConst;
import com.hao.movieshareback.dao.LogMapper;
import com.hao.movieshareback.dao.RateVideoCommentMapper;
import com.hao.movieshareback.model.Log;
import com.hao.movieshareback.model.RateVideoComment;
import com.hao.movieshareback.model.bo.VisitedRate;
import com.hao.movieshareback.service.LogService;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class RecommendByCF {

    @Autowired
    private RedisTemplate redisTemplate;

    public Map<Integer,List<RecommendedItem>> recommend(Integer neibor, Integer recommendNum, Map<Integer,List<VisitedRate>> data) throws TasteException {
        DataModel dataModel = createDataModel(data);
        Map<Integer,List<RecommendedItem>> result = new HashMap<>();
        //比较两个用户之间的相似度
        UserSimilarity similarity =
                new PearsonCorrelationSimilarity(dataModel);
        //明确与给定用户最相似的一组用户
        UserNeighborhood neighborhood = new NearestNUserNeighborhood(neibor, similarity,dataModel);
        //合并上述所有组件为用户推荐物品
        Recommender recommender = new GenericUserBasedRecommender(
                dataModel, neighborhood, similarity);
        dataModel.getUserIDs().forEachRemaining(userId-> {
            List<RecommendedItem> recommendations = null;
            try {
                recommendations = recommender.recommend(userId, recommendNum);
            } catch (TasteException e) {
                e.printStackTrace();
            }
            result.put(Math.toIntExact(userId),recommendations);
        }
        );
        if (result!=null) {
            for (Integer userId : result.keySet()) {
                List<RecommendedItem> recommendedItemList = result.get(userId);
                String cacheKey = SystemConst.USER_RECOMMEND_CACHE_PREFIX+userId;
                for (RecommendedItem recommendedItem:recommendedItemList){
                    Double oldSocre=redisTemplate.opsForZSet().score(cacheKey,recommendedItem.getItemID());
                    if (oldSocre==null||oldSocre<recommendedItem.getValue()){
                        redisTemplate.opsForZSet().add(cacheKey,recommendedItem.getItemID(),recommendedItem.getValue());
                    }
                }
            }
        }

        return result;
    }

    protected DataModel createDataModel(Map<Integer,List<VisitedRate>> data)  {
        FastByIDMap<PreferenceArray> preferenceArrayFastByIDMap = new FastByIDMap<>();
        for (Integer userId:data.keySet()){
            PreferenceArray preferenceArray=preferenceArrayFastByIDMap.get(userId);
            List<VisitedRate> visitedRates = data.get(userId);
            if (preferenceArray == null) {
                preferenceArray = new GenericUserPreferenceArray(visitedRates.size());
            }
            int i = 0;
            for (VisitedRate rate:visitedRates){
                preferenceArray.setUserID(i,rate.getUserId());
                preferenceArray.setItemID(i,rate.getVideoId());
                preferenceArray.setValue(i,rate.getRate().floatValue());
                i++;
            }

            preferenceArrayFastByIDMap.put(userId,preferenceArray);
        }
        return new GenericDataModel(preferenceArrayFastByIDMap);
    }

}
