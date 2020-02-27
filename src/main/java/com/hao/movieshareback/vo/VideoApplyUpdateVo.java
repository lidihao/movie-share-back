package com.hao.movieshareback.vo;

import java.util.List;

public class VideoApplyUpdateVo {
    private Integer videoApprovalId;
    private String title;
    private Integer pictureId;
    private String introduce;
    private List<Integer> appendVideoFile;
    private List<Integer> deleteVideoFile;

    public VideoApplyUpdateVo() {
    }

    public Integer getVideoApprovalId() {
        return videoApprovalId;
    }

    public void setVideoApprovalId(Integer videoApprovalId) {
        this.videoApprovalId = videoApprovalId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPictureId() {
        return pictureId;
    }

    public void setPictureId(Integer pictureId) {
        this.pictureId = pictureId;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public List<Integer> getAppendVideoFile() {
        return appendVideoFile;
    }

    public void setAppendVideoFile(List<Integer> appendVideoFile) {
        this.appendVideoFile = appendVideoFile;
    }

    public List<Integer> getDeleteVideoFile() {
        return deleteVideoFile;
    }

    public void setDeleteVideoFile(List<Integer> deleteVideoFile) {
        this.deleteVideoFile = deleteVideoFile;
    }
}
