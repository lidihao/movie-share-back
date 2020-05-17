package com.hao.movieshareback.model.bo;

import com.hao.movieshareback.model.Video;
import com.hao.movieshareback.vo.auth.UserVo;

public class VideoCommentMessage {
    private String commentContent;
    private Video video;
    private UserVo commentUserVo;

    public VideoCommentMessage() {
    }

    public VideoCommentMessage(String commentContent, Video video, UserVo commentUserVo) {
        this.commentContent = commentContent;
        this.video = video;
        this.commentUserVo = commentUserVo;
    }


    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public UserVo getCommentUserVo() {
        return commentUserVo;
    }

    public void setCommentUserVo(UserVo commentUserVo) {
        this.commentUserVo = commentUserVo;
    }
}
