package com.hao.movieshareback.tasks;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hao.movieshareback.dao.JavaSystemTaskMapper;
import com.hao.movieshareback.dao.VideoMapper;
import com.hao.movieshareback.model.task.JavaSystemTask;
import com.hao.movieshareback.service.VideoService;
import com.hao.movieshareback.utils.SpringContextJobUtil;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class ImportToElasticsearchJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        VideoMapper videoMapper = SpringContextJobUtil.getBean(VideoMapper.class);
        VideoService videoService = SpringContextJobUtil.getBean(VideoService.class);
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
        String params = systemTask.getParams();
        int pageSize=500;
        if (params!=null) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode=objectMapper.readTree(params);
                pageSize=jsonNode.asInt();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        doImportData(pageSize,videoMapper,videoService,jobExecutionContext);
    }

    protected void doImportData(Integer pageSize,VideoMapper videoMapper,VideoService videoService,JobExecutionContext jobExecutionContext){

    }
}
