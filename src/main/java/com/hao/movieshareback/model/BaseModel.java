package com.hao.movieshareback.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hao.movieshareback.utils.SecurityUtils;

import java.io.Serializable;
import java.util.Date;

public abstract class BaseModel implements Serializable {
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    protected Date createdTime;
    protected String createdBy;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    protected Date updatedTime;
    protected String updatedBy;
    protected boolean isDelete;

    public BaseModel() {
    }

    public static void setNewCreate(BaseModel model,String userName,Date createdTime){
        model.setCreatedBy(userName);
        model.setCreatedTime(createdTime);
    }

    public static void setUpdated(BaseModel model,String userName,Date updatedTime){
        model.setUpdatedBy(userName);
        model.setUpdatedTime(updatedTime);
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }
}
