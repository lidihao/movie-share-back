package com.hao.movieshareback.vo;

import com.hao.movieshareback.model.PrivateMessage;
import com.hao.movieshareback.vo.auth.UserVo;

public class MessageListVo {
    private UserVo user;
    private UserVo friend;
    private XPage<PrivateMessage> messagePage;

    public MessageListVo() {
    }

    public MessageListVo(UserVo user, UserVo friend, XPage<PrivateMessage> messagePage) {
        this.user = user;
        this.friend = friend;
        this.messagePage = messagePage;
    }

    public UserVo getUser() {
        return user;
    }

    public void setUser(UserVo user) {
        this.user = user;
    }

    public UserVo getFriend() {
        return friend;
    }

    public void setFriend(UserVo friend) {
        this.friend = friend;
    }

    public XPage<PrivateMessage> getMessagePage() {
        return messagePage;
    }

    public void setMessagePage(XPage<PrivateMessage> messagePage) {
        this.messagePage = messagePage;
    }
}
