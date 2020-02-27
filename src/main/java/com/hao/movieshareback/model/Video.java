package com.hao.movieshareback.model;

/**
 *  `video_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
 *     `video_title` VARCHAR(100) NOT NULL COMMENT '视频题目',
 *     `video_poster_url` VARCHAR(100) NOT NULL COMMENT '视频海报url',
 *     `video_play_count` BIGINT NOT NULL COMMENT '视频播放数',
 *     `video_comment_person` BIGINT NOT NULL COMMENT '视频评论人数',
 *     `video_desc` VARCHAR(400) NOT NULL COMMENT '视频简介',
 *     `video_rate` double NOT NULL DEFAULT 0.0 COMMENT '视频的评分,用于视频的推荐',
 *     `upload_user_id` int(11) NOT NULL COMMENT '视频上传user_id',
 *     `category_id` int(11) NOT NULL COMMENT '视频的类别',
 *     `created_time` DATETIME    COMMENT '创建时间' ,
 *     `created_by` VARCHAR(32)    COMMENT '创建时间',
 *     `updated_time` DATETIME    COMMENT '更新时间' ,
 *     `updated_by` VARCHAR(32)    COMMENT '更新人' ,
 *     `is_delete` int(1) NOT NULL  DEFAULT 0 COMMENT '是否删除',
 *     PRIMARY KEY (video_id),
 *     INDEX (is_delete)
 */
public class Video extends BaseModel{
    private Integer videoId;
    private String videoTitle;
    private Integer videoPosterId;
    private Long videoPlayCount;
    private Long videoCommentPerson;
    private String videoDesc;
    private Double videoRate;
    private Integer uploadUserId;
    private Integer categoryId;

    public Video() {
    }

    public Video(String videoTitle,
                 Integer videoPosterId, Long videoPlayCount,
                 Long videoCommentPerson, String videoDesc,
                 Double videoRate, Integer uploadUserId, Integer categoryId) {
        this.videoTitle = videoTitle;
        this.videoPosterId = videoPosterId;
        this.videoPlayCount = videoPlayCount;
        this.videoCommentPerson = videoCommentPerson;
        this.videoDesc = videoDesc;
        this.videoRate = videoRate;
        this.uploadUserId = uploadUserId;
        this.categoryId = categoryId;
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

    public Integer getVideoPosterId() {
        return videoPosterId;
    }

    public void setVideoPosterId(Integer videoPosterId) {
        this.videoPosterId = videoPosterId;
    }

    public Long getVideoPlayCount() {
        return videoPlayCount;
    }

    public void setVideoPlayCount(Long videoPlayCount) {
        this.videoPlayCount = videoPlayCount;
    }

    public Long getVideoCommentPerson() {
        return videoCommentPerson;
    }

    public void setVideoCommentPerson(Long videoCommentPerson) {
        this.videoCommentPerson = videoCommentPerson;
    }

    public String getVideoDesc() {
        return videoDesc;
    }

    public void setVideoDesc(String videoDesc) {
        this.videoDesc = videoDesc;
    }

    public Double getVideoRate() {
        return videoRate;
    }

    public void setVideoRate(Double videoRate) {
        this.videoRate = videoRate;
    }

    public Integer getUploadUserId() {
        return uploadUserId;
    }

    public void setUploadUserId(Integer uploadUserId) {
        this.uploadUserId = uploadUserId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
