package com.hao.movieshareback.vo;

public class MessageReceiver {
    private String messageContent;
    private Integer senderId;
    private Integer receiverId;

    public MessageReceiver() {
    }

    public MessageReceiver(String messageContent, Integer senderId, Integer receiverId) {
        this.messageContent = messageContent;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }
}
