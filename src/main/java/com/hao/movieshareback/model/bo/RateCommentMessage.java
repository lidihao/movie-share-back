package com.hao.movieshareback.model.bo;

import com.hao.movieshareback.model.Video;
import com.hao.movieshareback.vo.auth.UserVo;

public class RateCommentMessage {
    private UserVo userVo;
    private Video video;
    private String content;
    private Double rate;

    public RateCommentMessage() {
    }

    public RateCommentMessage(UserVo userVo, Video video, String content, Double rate) {
        this.userVo = userVo;
        this.video = video;
        this.content = content;
        this.rate = rate;
    }

    public UserVo getUserVo() {
        return userVo;
    }

    public void setUserVo(UserVo userVo) {
        this.userVo = userVo;
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
