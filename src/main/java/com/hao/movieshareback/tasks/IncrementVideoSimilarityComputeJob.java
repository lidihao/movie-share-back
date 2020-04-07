package com.hao.movieshareback.tasks;

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

public class IncrementVideoSimilarityComputeJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        VideoMapper videoMapper = SpringContextJobUtil.getBean(VideoMapper.class);
        TagMapper tagMapper = SpringContextJobUtil.getBean(TagMapper.class);
        RedisTemplate redisTemplate = (RedisTemplate) SpringContextJobUtil.getBean("redisTemplate");

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

        List<Tag> tagList = tagMapper.selectAllTag();
        int[] tagArr = new int[tagList.size()];
        int index=0;
        for (Tag tag:tagList){
            tagArr[index++]=tag.getTagId();
        }

        double similarityThreshold=0.5;
        int size=20;
        int processSize=10;
        String cacheKeyPrefix="SIMILARITY_";
        String queueName="COMPUTE_VIDEO_SIMILARITY_QUEUE";
        List<Video> videoList=videoMapper.getAllVideo();
        VideoTagSimilarityComputer videoTagSimilarityComputer = new VideoTagSimilarityComputer(tagArr,new CosineSimilarityComputer());
        for (int i=0;i<processSize;i++) {
            Object value=redisTemplate.opsForList().rightPop(queueName);
            if (value==null){
                return;
            }
            Integer videoId=(Integer) value;
            List<Integer> tagList1=tagMapper.selectTagByVideoId(videoId);
            for (Video video:videoList){
                if (video.getVideoId().equals(videoId)){
                    continue;
                }
                List<Integer> tagList2=tagMapper.selectTagByVideoId(video.getVideoId());
                double videoSimilarityRes = videoTagSimilarityComputer.computeVideoSimilarity(listToArr(tagList1),listToArr(tagList2));
                if (videoSimilarityRes>similarityThreshold) {
                    redisTemplate.opsForZSet().add(cacheKeyPrefix + videoId, video.getVideoId(), videoSimilarityRes);
                    redisTemplate.opsForZSet().add(cacheKeyPrefix + video.getVideoId(), videoId, videoSimilarityRes);
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
