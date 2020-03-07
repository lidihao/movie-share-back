package com.hao.movieshareback.model.bo;

public class PrivateMsgUserItem {
    private Integer friendId;
    private Integer messageId;

    public PrivateMsgUserItem() {
    }

    public PrivateMsgUserItem(Integer friendId, Integer messageId) {
        this.friendId = friendId;
        this.messageId = messageId;
    }

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }
}
