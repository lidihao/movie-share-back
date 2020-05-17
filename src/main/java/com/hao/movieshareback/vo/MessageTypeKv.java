package com.hao.movieshareback.vo;

public class MessageTypeKv {

    private String messageTypeDesc;
    private String messageType;

    public MessageTypeKv(String messageTypeDesc, String messageType) {
        this.messageTypeDesc = messageTypeDesc;
        this.messageType = messageType;
    }

    public String getMessageTypeDesc() {
        return messageTypeDesc;
    }

    public void setMessageTypeDesc(String messageTypeDesc) {
        this.messageTypeDesc = messageTypeDesc;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
}
