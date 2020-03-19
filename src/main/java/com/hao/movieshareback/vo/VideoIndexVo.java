package com.hao.movieshareback.vo;

import java.util.Date;

/**
 */
public class VideoIndexVo {
    private Integer videoId;
    private String videoTitle;
    private String videoDesc;
    private Long videoPlayCount;
    private Double videoRate;
    private Long videoCommentPerson;
    private Integer uploadUserId;
    private String uploadUserName;
    private Integer categoryId;
    private String categoryName;
    private String tags;
    private Date createdTime;
    private Integer isDelete;

    public VideoIndexVo() {
    }


    public VideoIndexVo(Integer videoId, String videoTitle, String videoDesc, Long videoPlayCount, Double videoRate, Long videoCommentPerson, Integer uploadUserId, String uploadUserName, Integer categoryId, String categoryName, String tags, Date createdTime, Integer isDelete) {
        this.videoId = videoId;
        this.videoTitle = videoTitle;
        this.videoDesc = videoDesc;
        this.videoPlayCount = videoPlayCount;
        this.videoRate = videoRate;
        this.videoCommentPerson = videoCommentPerson;
        this.uploadUserId = uploadUserId;
        this.uploadUserName = uploadUserName;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.tags = tags;
        this.createdTime = createdTime;
        this.isDelete = isDelete;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoDesc() {
        return videoDesc;
    }

    public void setVideoDesc(String videoDesc) {
        this.videoDesc = videoDesc;
    }

    public Long getVideoPlayCount() {
        return videoPlayCount;
    }

    public void setVideoPlayCount(Long videoPlayCount) {
        this.videoPlayCount = videoPlayCount;
    }

    public Double getVideoRate() {
        return videoRate;
    }

    public void setVideoRate(Double videoRate) {
        this.videoRate = videoRate;
    }

    public Long getVideoCommentPerson() {
        return videoCommentPerson;
    }

    public void setVideoCommentPerson(Long videoCommentPerson) {
        this.videoCommentPerson = videoCommentPerson;
    }

    public Integer getUploadUserId() {
        return uploadUserId;
    }

    public void setUploadUserId(Integer uploadUserId) {
        this.uploadUserId = uploadUserId;
    }

    public String getUploadUserName() {
        return uploadUserName;
    }

    public void setUploadUserName(String uploadUserName) {
        this.uploadUserName = uploadUserName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
