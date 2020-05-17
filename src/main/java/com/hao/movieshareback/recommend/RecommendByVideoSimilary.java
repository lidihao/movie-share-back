package com.hao.movieshareback.recommend;

import com.hao.movieshareback.config.SystemConst;
import com.hao.movieshareback.dao.VideoMapper;
import com.hao.movieshareback.model.Video;
import com.hao.movieshareback.model.bo.VisitedRate;
import com.hao.movieshareback.service.RecommendService;
import com.hao.movieshareback.vo.VideoDetailVo;
import com.hao.movieshareback.vo.XPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class RecommendByVideoSimilary {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RecommendService recommendService;

    @Autowired
    private VideoMapper videoMapper;

    public void recommend(Map<Integer, List<VisitedRate>> data){
        for (Integer userId:data.keySet()){
            List<VisitedRate> visitedRateList = data.get(userId);
            String cacheKey = SystemConst.USER_RECOMMEND_CACHE_PREFIX+userId;
            for (VisitedRate visitedRate:visitedRateList){
                Integer videoId = visitedRate.getVideoId();
                Set<ZSetOperations.TypedTuple<Integer>> similarityVideoSet =redisTemplate.opsForZSet().reverseRangeWithScores(SystemConst.VIDEO_SIMILARITY_CACHE_PREFIX+videoId,0,20);

                if (similarityVideoSet!=null) {
                    for (ZSetOperations.TypedTuple<Integer> tuple : similarityVideoSet) {
                        Integer similaryVideoId = tuple.getValue();
                        Double newScore = visitedRate.getRate()*tuple.getScore();
                        if (videoMapper.getVideo(similaryVideoId)==null){
                            continue;
                        }
                        Double oldSocre=redisTemplate.opsForZSet().score(cacheKey,similaryVideoId);
                        if (oldSocre==null||oldSocre<newScore){
                            redisTemplate.opsForZSet().add(cacheKey,similaryVideoId,newScore);
                        }
                    }
                }
            }
        }
    }
}
