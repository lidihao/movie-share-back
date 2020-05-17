package com.hao.movieshareback.model.bo;

import com.hao.movieshareback.model.Video;

public class RateCommentMessage {
    private Integer userId;
    private Video video;
    private String content;
    private Double rate;

    public RateCommentMessage() {
    }

    public RateCommentMessage(Integer userId, Video video, String content, Double rate) {
        this.userId = userId;
        this.video = video;
        this.content = content;
        this.rate = rate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
