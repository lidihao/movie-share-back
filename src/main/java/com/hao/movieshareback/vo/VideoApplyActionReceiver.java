package com.hao.movieshareback.vo;

public class VideoApplyActionReceiver {
    private Integer videoApprovalId;
    private Integer applystatus;

    public VideoApplyActionReceiver() {
    }

    public Integer getVideoApprovalId() {
        return videoApprovalId;
    }

    public void setVideoApprovalId(Integer videoApprovalId) {
        this.videoApprovalId = videoApprovalId;
    }

    public Integer getApplystatus() {
        return applystatus;
    }

    public void setApplystatus(Integer applystatus) {
        this.applystatus = applystatus;
    }

    @Override
    public String toString() {
        return "VideoApplyActionReceiver{" +
                "videoApprovalId=" + videoApprovalId +
                ", applystatus=" + applystatus +
                '}';
    }
}
