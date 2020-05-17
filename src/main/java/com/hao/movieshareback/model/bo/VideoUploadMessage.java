package com.hao.movieshareback.model.bo;

public class VideoUploadMessage {
    private Integer uploadUserId;
    private Integer followingUserId;
    private Integer videoId;

    public VideoUploadMessage() {
    }

    public VideoUploadMessage(Integer uploadUserId, Integer followingUserId, Integer videoId) {
        this.uploadUserId = uploadUserId;
        this.followingUserId = followingUserId;
        this.videoId = videoId;
    }

    public Integer getUploadUserId() {
        return uploadUserId;
    }

    public void setUploadUserId(Integer uploadUserId) {
        this.uploadUserId = uploadUserId;
    }

    public Integer getFollowingUserId() {
        return followingUserId;
    }

    public void setFollowingUserId(Integer followingUserId) {
        this.followingUserId = followingUserId;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }
}
