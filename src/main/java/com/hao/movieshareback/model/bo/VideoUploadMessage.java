package com.hao.movieshareback.model.bo;

import com.hao.movieshareback.model.Video;
import com.hao.movieshareback.vo.auth.UserVo;

public class VideoUploadMessage {
    private UserVo uploadUser;
    private Integer followingUserId;
    private Video video;

    public VideoUploadMessage() {
    }

    public VideoUploadMessage(UserVo uploadUser, Integer followingUserId, Video video) {
        this.uploadUser = uploadUser;
        this.followingUserId = followingUserId;
        this.video = video;
    }

    public UserVo getUploadUser() {
        return uploadUser;
    }

    public void setUploadUser(UserVo uploadUser) {
        this.uploadUser = uploadUser;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Integer getFollowingUserId() {
        return followingUserId;
    }

    public void setFollowingUserId(Integer followingUserId) {
        this.followingUserId = followingUserId;
    }
}
