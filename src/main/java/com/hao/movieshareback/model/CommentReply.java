package com.hao.movieshareback.model;

public class CommentReply extends BaseModel{
    private Integer replyId;
    private String replyContent;
    private Long replyUp;
    private Long replyDown;
    private Integer replyUserId;
    private Integer videoCommentId;
    private Integer replyToId;

    public CommentReply() {
    }

    public CommentReply(Integer replyId, String replyContent, Long replyUp, Long replyDown, Integer replyUserId, Integer videoCommentId, Integer replyToId) {
        this.replyId = replyId;
        this.replyContent = replyContent;
        this.replyUp = replyUp;
        this.replyDown = replyDown;
        this.replyUserId = replyUserId;
        this.videoCommentId = videoCommentId;
        this.replyToId = replyToId;
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

    public Integer getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(Integer replyUserId) {
        this.replyUserId = replyUserId;
    }

    public Integer getVideoCommentId() {
        return videoCommentId;
    }

    public void setVideoCommentId(Integer videoCommentId) {
        this.videoCommentId = videoCommentId;
    }

    public Integer getReplyToId() {
        return replyToId;
    }

    public void setReplyToId(Integer replyToId) {
        this.replyToId = replyToId;
    }
}
