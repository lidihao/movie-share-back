package com.hao.movieshareback.model;

public class Follow extends BaseModel{
    private Integer followId;
    private Integer userId;
    private Integer followedUserId;


    public Follow() {
    }

    public Follow(Integer followId, Integer userId, Integer followedUserId) {
        this.followId = followId;
        this.userId = userId;
        this.followedUserId = followedUserId;
    }

    public Integer getFollowId() {
        return followId;
    }

    public void setFollowId(Integer followId) {
        this.followId = followId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFollowedUserId() {
        return followedUserId;
    }

    public void setFollowedUserId(Integer followedUserId) {
        this.followedUserId = followedUserId;
    }
}
