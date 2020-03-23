package com.hao.movieshareback.recommend;

import com.hao.movieshareback.dao.RateVideoCommentMapper;
import com.hao.movieshareback.model.RateVideoComment;
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

import java.util.List;

@Component
public class RecommendByCF {

    @Autowired
    private RateVideoCommentMapper videoCommentMapper;

    @Autowired
    private RedisTemplate redisTemplate;


    public void recommend(Integer neibor,Integer recommendNum) throws TasteException {
        DataModel dataModel = createDataModel();
        //比较两个用户之间的相似度
        UserSimilarity similarity =
                new PearsonCorrelationSimilarity(dataModel);
        //明确与给定用户最相似的一组用户
        UserNeighborhood neighborhood = new NearestNUserNeighborhood(neibor, similarity,dataModel);
        //合并上述所有组件为用户推荐物品
        Recommender recommender = new GenericUserBasedRecommender(
                dataModel, neighborhood, similarity);
        String cachePrefix="RECOMMEND_BY_USER_BASE_CF";
        dataModel.getUserIDs().forEachRemaining(userId-> {
            List<RecommendedItem> recommendations = null;
            try {
                recommendations = recommender.recommend(userId, recommendNum);
            } catch (TasteException e) {
                e.printStackTrace();
            }
            for (RecommendedItem recommendation : recommendations) {
                redisTemplate.opsForZSet().add(cachePrefix+"_"+userId,
                        recommendation.getItemID(),recommendation.getValue());
                System.out.println(recommendation);
            }
        }
        );
    }

    protected DataModel createDataModel(){
        List<RateVideoComment> rateVideoCommentList = videoCommentMapper.selectAllRateComment();
        FastByIDMap<PreferenceArray> preferenceArrayFastByIDMap = new FastByIDMap<>();
        for (RateVideoComment rateVideoComment:rateVideoCommentList){
            PreferenceArray preferenceArray=preferenceArrayFastByIDMap.get(rateVideoComment.getCommentUserId());
            if (preferenceArray == null) {
                preferenceArray = new GenericUserPreferenceArray(1);
            } else {
                PreferenceArray newPrefs = new GenericUserPreferenceArray(preferenceArray.length() + 1);
                for (int i = 0, j = 1; i < preferenceArray.length(); i++, j++) {
                    newPrefs.set(j, preferenceArray.get(i));
                }
                preferenceArray = newPrefs;
            }
            preferenceArray.setUserID(0,rateVideoComment.getCommentUserId());
            preferenceArray.setItemID(0,rateVideoComment.getVideoId());
            preferenceArray.setValue(0,rateVideoComment.getRate().floatValue());
            preferenceArrayFastByIDMap.put(rateVideoComment.getCommentUserId(),preferenceArray);
        }
        return new GenericDataModel(preferenceArrayFastByIDMap);
    }
}
