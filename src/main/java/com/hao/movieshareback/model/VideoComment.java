package com.hao.movieshareback.model;

public class VideoComment extends BaseModel{
    private Integer commentId;
    private String commentContent;
    private Long commentUp;
    private Long commentDown;
    private Integer commentUserId;
    private Double rate;
    private Integer videoId;

    public VideoComment() {
    }

    public VideoComment(Integer commentId, String commentContent, Long commentUp, Long commentDown, Integer commentUserId, Double rate, Integer videoId) {
        this.commentId = commentId;
        this.commentContent = commentContent;
        this.commentUp = commentUp;
        this.commentDown = commentDown;
        this.commentUserId = commentUserId;
        this.rate = rate;
        this.videoId = videoId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Long getCommentUp() {
        return commentUp;
    }

    public void setCommentUp(Long commentUp) {
        this.commentUp = commentUp;
    }

    public Long getCommentDown() {
        return commentDown;
    }

    public void setCommentDown(Long commentDown) {
        this.commentDown = commentDown;
    }

    public Integer getCommentUserId() {
        return commentUserId;
    }

    public void setCommentUserId(Integer commentUserId) {
        this.commentUserId = commentUserId;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }
}
