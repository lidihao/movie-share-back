package com.hao.movieshareback.model.type;

public enum MessageType {
    COMMENT_MESSAGE("comment_message",1,"评论提醒"),
    RATE_COMMENT_MESSAGE("rate_comment_message",2,"点评提醒"),
    VIDEO_UPLOAD_MESSAGE("video_upload_message",3,"视频上传提醒"),
    VIDEO_APPROVAL_MESSAGE("video_approval_message",4,"视频审批提醒");

    private String messageType;
    private Integer tag;
    private String desc;

    MessageType(String messageType, Integer tag,String desc) {
        this.messageType = messageType;
        this.tag = tag;
        this.desc=desc;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
