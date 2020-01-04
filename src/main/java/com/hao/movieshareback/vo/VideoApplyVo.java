package com.hao.movieshareback.vo;

import com.hao.movieshareback.vo.auth.UserVo;

import java.util.Date;

public class VideoApplyVo {
    private Integer videoApprovalId;
    private Integer videoId;
    private String title;
    private String posterUrl;
    private UserVo userVo;
    private Date createdTime;


    public VideoApplyVo(Integer videoApprovalId, Integer videoId, String title, String posterUrl, UserVo userVo, Date createdTime) {
        this.videoApprovalId = videoApprovalId;
        this.videoId = videoId;
        this.title = title;
        this.posterUrl = posterUrl;
        this.userVo = userVo;
        this.createdTime = createdTime;
    }

    public Integer getVideoApprovalId() {
        return videoApprovalId;
    }

    public void setVideoApprovalId(Integer videoApprovalId) {
        this.videoApprovalId = videoApprovalId;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public UserVo getUserVo() {
        return userVo;
    }

    public void setUserVo(UserVo userVo) {
        this.userVo = userVo;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}
