package com.hao.movieshareback.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hao.movieshareback.vo.auth.UserVo;

import java.util.Date;

public class VideoCommentVo {
    private Integer commentId;
    private String commentContent;
    private Long commentUp;
    private Long commentDown;
    private Double rate;
    private Integer videoId;
    private UserVo commentUser;
    private Date createTime;

    public VideoCommentVo() {
    }

    public VideoCommentVo(Integer commentId, String commentContent, Long commentUp, Long commentDown, Double rate, Integer videoId, UserVo commentUser, Date createTime) {
        this.commentId = commentId;
        this.commentContent = commentContent;
        this.commentUp = commentUp;
        this.commentDown = commentDown;
        this.rate = rate;
        this.videoId = videoId;
        this.commentUser = commentUser;
        this.createTime = createTime;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Long getCommentUp() {
        return commentUp;
    }

    public void setCommentUp(Long commentUp) {
        this.commentUp = commentUp;
    }

    public Long getCommentDown() {
        return commentDown;
    }

    public void setCommentDown(Long commentDown) {
        this.commentDown = commentDown;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public UserVo getCommentUser() {
        return commentUser;
    }

    public void setCommentUser(UserVo commentUser) {
        this.commentUser = commentUser;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
