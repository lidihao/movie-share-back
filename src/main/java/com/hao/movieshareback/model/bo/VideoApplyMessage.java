package com.hao.movieshareback.model.bo;

import com.hao.movieshareback.model.Video;
import com.hao.movieshareback.model.VideoApproval;
import com.hao.movieshareback.model.type.ApprovalType;

public class VideoApplyMessage {
    private Video video;
    private VideoApproval videoApproval;
    private ApprovalType approvalType;

    public VideoApplyMessage() {
    }

    public VideoApplyMessage(Video video, VideoApproval videoApproval, ApprovalType approvalType) {
        this.video = video;
        this.videoApproval = videoApproval;
        this.approvalType = approvalType;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public VideoApproval getVideoApproval() {
        return videoApproval;
    }

    public void setVideoApproval(VideoApproval videoApproval) {
        this.videoApproval = videoApproval;
    }

    public ApprovalType getApprovalType() {
        return approvalType;
    }

    public void setApprovalType(ApprovalType approvalType) {
        this.approvalType = approvalType;
    }
}
