package com.hao.movieshareback.model.task;

import com.hao.movieshareback.model.BaseModel;

public class SystemTask extends BaseModel {
    protected Integer systemTaskId;
    protected String systemTaskName;
    protected String systemTaskType;
    protected String jobName;
    protected String jobGroupName;
    protected String triggerName;
    protected String triggerGroupName;
    protected String systemTaskDesc;
    protected String params;
    protected String cron;
    protected String jobStatus;

    public SystemTask() {
    }


    public Integer getSystemTaskId() {
        return systemTaskId;
    }

    public void setSystemTaskId(Integer systemTaskId) {
        this.systemTaskId = systemTaskId;
    }

    public String getSystemTaskName() {
        return systemTaskName;
    }

    public void setSystemTaskName(String systemTaskName) {
        this.systemTaskName = systemTaskName;
    }

    public String getSystemTaskType() {
        return systemTaskType;
    }

    public void setSystemTaskType(String systemTaskType) {
        this.systemTaskType = systemTaskType;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroupName() {
        return jobGroupName;
    }

    public void setJobGroupName(String jobGroupName) {
        this.jobGroupName = jobGroupName;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    public String getTriggerGroupName() {
        return triggerGroupName;
    }

    public void setTriggerGroupName(String triggerGroupName) {
        this.triggerGroupName = triggerGroupName;
    }

    public String getSystemTaskDesc() {
        return systemTaskDesc;
    }

    public void setSystemTaskDesc(String systemTaskDesc) {
        this.systemTaskDesc = systemTaskDesc;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }
}
