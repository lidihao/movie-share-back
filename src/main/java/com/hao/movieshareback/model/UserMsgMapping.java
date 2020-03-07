package com.hao.movieshareback.model;

import java.util.Date;

public class UserMsgMapping {
    private Integer mappingId;
    private Integer messageId;
    private boolean unRead;
    private Integer friendId;
    private Integer userId;
    private Date createdTime;
    private boolean isDelete;

    public UserMsgMapping() {
    }

    public UserMsgMapping(Integer userId, Integer friendId, Date createdTime,Integer messageId) {
        this.friendId = friendId;
        this.userId = userId;
        this.createdTime = createdTime;
        this.messageId=messageId;
    }

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getMappingId() {
        return mappingId;
    }

    public void setMappingId(Integer mappingId) {
        this.mappingId = mappingId;
    }

    public boolean isUnRead() {
        return unRead;
    }

    public void setUnRead(boolean unRead) {
        this.unRead = unRead;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }
}
