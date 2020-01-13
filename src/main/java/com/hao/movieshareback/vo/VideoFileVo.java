package com.hao.movieshareback.vo;

public class VideoFileVo {
    private Integer videoFileId;
    private Integer sort;
    private String fileName;
    private String url;
    private String posterUrl;
    private String approvalType;

    public VideoFileVo(Integer videoFileId, Integer sort,
                       String fileName, String url, String posterUrl,
                       String approvalType) {
        this.videoFileId = videoFileId;
        this.sort = sort;
        this.fileName = fileName;
        this.url = url;
        this.posterUrl = posterUrl;
        this.approvalType = approvalType;
    }

    public Integer getVideoFileId() {
        return videoFileId;
    }

    public void setVideoFileId(Integer videoFileId) {
        this.videoFileId = videoFileId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getApprovalType() {
        return approvalType;
    }

    public void setApprovalType(String approvalType) {
        this.approvalType = approvalType;
    }
}
