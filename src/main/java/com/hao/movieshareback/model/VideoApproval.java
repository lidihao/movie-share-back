package com.hao.movieshareback.model;

/**
 *  `video_approval_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
 *     `upload_user_id` int(11) NOT NULL COMMENT '上传人id',
 *     `poster_id` int(11) NOT NULL COMMENT '视频posterURL',
 *     `title` VARCHAR(100) NOT NULL COMMENT '视频题目审批',
 *     `introduce` VARCHAR(200) NOT NULL COMMENT '视频简介',
 *     `category_id` int(11) NOT NULL COMMENT '视频类别',
 *     `video_id` int(11) NOT NULL DEFAULT -1 COMMENT '对应的视频id',
 *     `approval_type` int NOT NULL DEFAULT 0 COMMENT '审批状态',
 *     `created_time` DATETIME    COMMENT '创建时间' ,
 *     `created_by` VARCHAR(32)    COMMENT '创建时间',
 *     `updated_time` DATETIME    COMMENT '更新时间' ,
 *     `updated_by` VARCHAR(32)    COMMENT '更新人' ,
 *     `is_delete` int(1) NOT NULL  DEFAULT 0 COMMENT '是否删除',
 *     PRIMARY KEY (video_approval_id),
 *     INDEX (is_delete)
 */
public class VideoApproval extends BaseModel{
    private Integer videoApprovalId;
    private Integer uploadUserId;
    private Integer posterId;
    private String title;
    private String introduce;
    private Integer categoryId;
    private Integer videoId;
    private Integer approvalType;

    public VideoApproval() {
    }

    public VideoApproval(Integer uploadUserId, Integer posterId, String title, String introduce, Integer categoryId, Integer approvalType) {
        this.uploadUserId = uploadUserId;
        this.posterId = posterId;
        this.title = title;
        this.introduce = introduce;
        this.categoryId = categoryId;
        this.approvalType = approvalType;
    }

    public Integer getVideoApprovalId() {
        return videoApprovalId;
    }

    public void setVideoApprovalId(Integer videoApprovalId) {
        this.videoApprovalId = videoApprovalId;
    }

    public Integer getUploadUserId() {
        return uploadUserId;
    }

    public void setUploadUserId(Integer uploadUserId) {
        this.uploadUserId = uploadUserId;
    }

    public Integer getPosterId() {
        return posterId;
    }

    public void setPosterId(Integer posterId) {
        this.posterId = posterId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public Integer getApprovalType() {
        return approvalType;
    }

    public void setApprovalType(Integer approvalType) {
        this.approvalType = approvalType;
    }
}
