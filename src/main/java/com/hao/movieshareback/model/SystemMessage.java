package com.hao.movieshareback.model;

public class SystemMessage extends BaseModel{
    private Integer systemMessageId;
    private String systemMessageType;
    private String systemMessageParams;
    private String systemMessageContent;
    private Integer userId;

    public SystemMessage(String systemMessageType, String systemMessageParams, String systemMessageContent, Integer userId) {
        this.systemMessageType = systemMessageType;
        this.systemMessageParams = systemMessageParams;
        this.systemMessageContent = systemMessageContent;
        this.userId = userId;
    }

    public SystemMessage() {
    }

    public Integer getSystemMessageId() {
        return systemMessageId;
    }

    public void setSystemMessageId(Integer systemMessageId) {
        this.systemMessageId = systemMessageId;
    }

    public String getSystemMessageType() {
        return systemMessageType;
    }

    public void setSystemMessageType(String systemMessageType) {
        this.systemMessageType = systemMessageType;
    }

    public String getSystemMessageParams() {
        return systemMessageParams;
    }

    public void setSystemMessageParams(String systemMessageParams) {
        this.systemMessageParams = systemMessageParams;
    }

    public String getSystemMessageContent() {
        return systemMessageContent;
    }

    public void setSystemMessageContent(String systemMessageContent) {
        this.systemMessageContent = systemMessageContent;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
