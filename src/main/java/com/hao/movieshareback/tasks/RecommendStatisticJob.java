package com.hao.movieshareback.tasks;

import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hao.movieshareback.dao.JavaSystemTaskMapper;
import com.hao.movieshareback.model.task.JavaSystemTask;
import com.hao.movieshareback.recommend.RecommendByStatistics;
import com.hao.movieshareback.utils.SpringContextJobUtil;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RecommendStatisticJob extends QuartzJobBean {

    /**
     *
     {
     "offsetDay":-10,
     "weights":{
     "play_video":0.2,
     "comment_video":0.4,
     "favorite_video":0.4,
     "log_weight":0.4,
     "rate_weight":0.6
     }}
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
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
        String taskParams=systemTask.getParams();
        ObjectMapper objectMapper = new ObjectMapper();
        Integer offsetDay=-30;
        Map<String,Double> weights= new HashMap<>();
        try {
            JsonNode jsonNode= objectMapper.readTree(taskParams);
            offsetDay=jsonNode.get("offsetDay").asInt();
            JsonNode weightsJsonNod=jsonNode.get("weights");
            weightsJsonNod.fieldNames().forEachRemaining(fieldName->{
                weights.put(fieldName,weightsJsonNod.get(fieldName).asDouble());
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Date endDate =jobExecutionContext.getFireTime();
        Date startDate = DateUtil.offsetDay(endDate,offsetDay);
        RecommendByStatistics recommendByStatistics = SpringContextJobUtil.getBean(RecommendByStatistics.class);

        try {
            recommendByStatistics.recommend(DateUtil.formatDateTime(startDate),DateUtil.formatDateTime(endDate),weights);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
