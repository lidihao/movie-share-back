package com.hao.movieshareback.vo;

import com.hao.movieshareback.vo.auth.UserVo;

import java.util.Date;

public class CommentReplyVo {
    private Integer replyId;
    private String replyContent;
    private Long replyUp;
    private Long replyDown;
    private UserVo replyUser;
    private Integer videoCommentId;
    private Date createTime;
    private CommentReplyVo replyToComment;

    public CommentReplyVo() {
    }

    public CommentReplyVo(Integer replyId, String replyContent, Long replyUp, Long replyDown, UserVo replyUser, Integer videoCommentId, Date createTime, CommentReplyVo replyToComment) {
        this.replyId = replyId;
        this.replyContent = replyContent;
        this.replyUp = replyUp;
        this.replyDown = replyDown;
        this.replyUser = replyUser;
        this.videoCommentId = videoCommentId;
        this.createTime = createTime;
        this.replyToComment = replyToComment;
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public Long getReplyUp() {
        return replyUp;
    }

    public void setReplyUp(Long replyUp) {
        this.replyUp = replyUp;
    }

    public Long getReplyDown() {
        return replyDown;
    }

    public void setReplyDown(Long replyDown) {
        this.replyDown = replyDown;
    }

    public UserVo getReplyUser() {
        return replyUser;
    }

    public void setReplyUser(UserVo replyUser) {
        this.replyUser = replyUser;
    }

    public Integer getVideoCommentId() {
        return videoCommentId;
    }

    public void setVideoCommentId(Integer videoCommentId) {
        this.videoCommentId = videoCommentId;
    }

    public CommentReplyVo getReplyToComment() {
        return replyToComment;
    }

    public void setReplyToComment(CommentReplyVo replyToComment) {
        this.replyToComment = replyToComment;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
