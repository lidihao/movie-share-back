package com.hao.movieshareback.tasks;

import com.hao.movieshareback.config.SystemConst;
import com.hao.movieshareback.dao.JavaSystemTaskMapper;
import com.hao.movieshareback.dao.TagMapper;
import com.hao.movieshareback.dao.VideoMapper;
import com.hao.movieshareback.model.Tag;
import com.hao.movieshareback.model.Video;
import com.hao.movieshareback.model.task.JavaSystemTask;
import com.hao.movieshareback.recommend.utils.CosineSimilarityComputer;
import com.hao.movieshareback.recommend.utils.VideoTagSimilarityComputer;
import com.hao.movieshareback.utils.SpringContextJobUtil;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FullVideoSimilarityComputeJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        VideoMapper videoMapper = SpringContextJobUtil.getBean(VideoMapper.class);
        TagMapper tagMapper = SpringContextJobUtil.getBean(TagMapper.class);
        RedisTemplate redisTemplate = (RedisTemplate) SpringContextJobUtil.getBean("redisTemplate");

        List<Video> videos = videoMapper.getAllVideo();
        Video[] videoArr = videos.toArray(new Video[]{});
        videos=null;

        JavaSystemTaskMapper javaSystemTaskMapper = SpringContextJobUtil.getBean(JavaSystemTaskMapper.class);
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        JavaSystemTask systemTask = null;
        if (jobDataMap!=null){
            Integer systemTaskId = jobDataMap.getInt("systemTaskId");
            systemTask = javaSystemTaskMapper.selectTaskByTaskId(systemTaskId);
        }
        if (systemTask==null){
            return;
        }

        double similarityThreshold=0.5;
        double size=20;

        List<Tag> tagList = tagMapper.selectAllTag();
        int[] tagArr = new int[tagList.size()];
        int index=0;
        for (Tag tag:tagList){
            tagArr[index++]=tag.getTagId();
        }
        String cacheKeyPrefix= SystemConst.VIDEO_SIMILARITY_CACHE_PREFIX;
        VideoTagSimilarityComputer videoTagSimilarityComputer = new VideoTagSimilarityComputer(tagArr,new CosineSimilarityComputer());
        Set<String> keyset=redisTemplate.keys(cacheKeyPrefix+"*");
        redisTemplate.delete(keyset);

        for (int i=0;i<videoArr.length;i++){
            for (int j=i+1;j<videoArr.length;j++){
                List<Integer> tagList1=tagMapper.selectTagByVideoId(videoArr[i].getVideoId());
                List<Integer> tagList2=tagMapper.selectTagByVideoId(videoArr[j].getVideoId());
                double videoSimilarityRes=videoTagSimilarityComputer.computeVideoSimilarity(listToArr(tagList1),listToArr(tagList2));

                if (videoSimilarityRes>similarityThreshold) {
                    redisTemplate.opsForZSet().add(cacheKeyPrefix + videoArr[i].getVideoId(), videoArr[j].getVideoId(), videoSimilarityRes);
                    redisTemplate.opsForZSet().add(cacheKeyPrefix + videoArr[j].getVideoId(), videoArr[i].getVideoId(), videoSimilarityRes);
                }
            }
        }
    }

    private int[] listToArr(List<Integer> integerList){
        int indx = 0;
        int[] res = new int[integerList.size()];
        for (Integer item:integerList){
            res[indx++]=item;
        }
        return res;
    }
}
