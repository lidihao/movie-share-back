package com.hao.movieshareback.model;

/**
 *create table `video_file`(
 *     `video_file_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
 *     `file_name` VARCHAR(100) NOT NULL COMMENT '名字',
 *     `approval_type` int NOT NULL DEFAULT 0 COMMENT '审批状态',
 *     `file_url` VARCHAR(100) NOT NULL COMMENT '视频url',
 *     `file_type` varchar(50) NOT NULL COMMENT '视频类型',
 *     `created_time` DATETIME    COMMENT '创建时间' ,
 *     `created_by` VARCHAR(32)    COMMENT '创建时间',
 *     `updated_time` DATETIME    COMMENT '更新时间' ,
 *     `updated_by` VARCHAR(32)    COMMENT '更新人' ,
 *     `is_delete` int(1) NOT NULL  DEFAULT 0 COMMENT '是否删除',
 *     PRIMARY KEY (video_file_id),
 *     INDEX (is_delete)
 * )COMMENT '上传的文件' ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1000;
 */
public class VideoFile extends BaseModel{
    private Integer videoFileId;
    private String fileName;
    private Long size;
    private Integer uploaderId;
    private Integer videoApplyId;
    private int approvalType;
    private String fileUrl;
    private String fileType;

    public VideoFile(String fileName, Long size, Integer uploaderId, int approvalType, String fileUrl, String fileType) {
        this.fileName = fileName;
        this.size = size;
        this.uploaderId = uploaderId;
        this.approvalType = approvalType;
        this.fileUrl = fileUrl;
        this.fileType = fileType;
    }

    public Integer getVideoApplyId() {
        return videoApplyId;
    }

    public void setVideoApplyId(Integer videoApplyId) {
        this.videoApplyId = videoApplyId;
    }

    public Integer getVideoFileId() {
        return videoFileId;
    }

    public void setVideoFileId(Integer videoFileId) {
        this.videoFileId = videoFileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Integer getUploaderId() {
        return uploaderId;
    }

    public void setUploaderId(Integer uploaderId) {
        this.uploaderId = uploaderId;
    }

    public int getApprovalType() {
        return approvalType;
    }

    public void setApprovalType(int approvalType) {
        this.approvalType = approvalType;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
