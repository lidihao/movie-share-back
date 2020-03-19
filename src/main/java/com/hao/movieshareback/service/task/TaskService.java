package com.hao.movieshareback.service.task;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.hao.movieshareback.dao.JavaSystemTaskMapper;
import com.hao.movieshareback.model.BaseModel;
import com.hao.movieshareback.model.task.JavaSystemTask;
import com.hao.movieshareback.model.task.TaskStatus;
import com.hao.movieshareback.utils.SecurityUtils;
import org.quartz.JobDataMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private JavaSystemTaskMapper javaSystemTaskMapper;
    @Autowired
    private TaskManager taskManager;

    @Transactional
    public void addJavaSystemTask(JavaSystemTask javaSystemTask){
        String userName = SecurityUtils.getUsername();
        BaseModel.setUpdated(javaSystemTask,userName,new Date());
        BaseModel.setNewCreate(javaSystemTask,userName,new Date());
        javaSystemTask.setJobStatus(TaskStatus.RUNNING.getTag());
        javaSystemTaskMapper.save(javaSystemTask);
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.putAsString("systemTaskId",javaSystemTask.getSystemTaskId());
        taskManager.addJob(javaSystemTask.getJobName(),javaSystemTask.getJobGroupName(),
                javaSystemTask.getSystemTaskDesc(),javaSystemTask.getTriggerName(),javaSystemTask.getTriggerGroupName(),
                javaSystemTask.getCron(),javaSystemTask.getClassName(),jobDataMap);
    }

    public List<JavaSystemTask> getTaskList(){
        return javaSystemTaskMapper.selectTask();
    }

    public JavaSystemTask getTaskById(Integer systemTaskId){
        return javaSystemTaskMapper.selectTaskByTaskId(systemTaskId);
    }

    public void updateTask(JavaSystemTask systemTask){
        javaSystemTaskMapper.updateTask(systemTask);
        taskManager.updateJobTime(systemTask.getTriggerName(),systemTask.getTriggerGroupName(),systemTask.getCron());
    }

    public void scheduleTask(Integer systemTaskId){
        JavaSystemTask systemTask = javaSystemTaskMapper.selectTaskByTaskId(systemTaskId);
        if (TaskStatus.PAUSE.getTag().equals(systemTask.getJobStatus())) {
            javaSystemTaskMapper.updateTaskStatus(TaskStatus.RUNNING.getTag(),systemTaskId);
            taskManager.resumeJob(systemTask.getJobName(), systemTask.getJobGroupName());
        }
    }

    public void stopTask(Integer systemTaskId){
        JavaSystemTask systemTask = javaSystemTaskMapper.selectTaskByTaskId(systemTaskId);
        if (TaskStatus.RUNNING.getTag().equals(systemTask.getJobStatus())) {
            javaSystemTaskMapper.updateTaskStatus(TaskStatus.PAUSE.getTag(),systemTaskId);
            taskManager.pauseJob(systemTask.getJobName(), systemTask.getJobGroupName());
        }
    }

    public void deleteTask(Integer systemTaskId){
        JavaSystemTask javaSystemTask = javaSystemTaskMapper.selectTaskByTaskId(systemTaskId);
        javaSystemTaskMapper.deleteTaskByTaskId(systemTaskId);
        taskManager.removeJob(javaSystemTask.getJobName(),javaSystemTask.getJobGroupName(),javaSystemTask.getTriggerName(),javaSystemTask.getTriggerGroupName());
    }
}
