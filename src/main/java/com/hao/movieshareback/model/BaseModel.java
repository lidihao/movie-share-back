package com.hao.movieshareback.model;

import java.io.Serializable;
import java.util.Date;

public abstract class BaseModel implements Serializable {
    protected Date createdTime;
    protected String createdBy;
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
