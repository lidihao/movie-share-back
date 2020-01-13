package com.hao.movieshareback.model;

/**
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
    private Integer posterId;
    private Integer sort;

    public VideoFile() {
    }

    public VideoFile(String fileName, Long size, Integer uploaderId, int approvalType, String fileUrl, String fileType, Integer posterId) {
        this.fileName = fileName;
        this.size = size;
        this.uploaderId = uploaderId;
        this.approvalType = approvalType;
        this.fileUrl = fileUrl;
        this.fileType = fileType;
        this.posterId = posterId;
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

    public Integer getPosterId() {
        return posterId;
    }

    public void setPosterId(Integer posterId) {
        this.posterId = posterId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
