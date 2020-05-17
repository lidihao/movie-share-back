package com.hao.movieshareback.model.bo;

public class VisitedRate{
    private Integer userId;
    private Integer videoId;
    private Double rate;

    public VisitedRate(Integer userId, Integer videoId, Double rate) {
        this.userId = userId;
        this.videoId = videoId;
        this.rate = rate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
