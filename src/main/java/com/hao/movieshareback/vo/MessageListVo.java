package com.hao.movieshareback.vo;

import com.hao.movieshareback.model.PrivateMessage;
import com.hao.movieshareback.vo.auth.UserVo;

import java.util.List;

public class MessageListVo {
    private UserVo user;
    private UserVo friend;
    private List<PrivateMessage> messagePage;

    public MessageListVo() {
    }

    public MessageListVo(UserVo user, UserVo friend, List<PrivateMessage> messagePage) {
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

    public List<PrivateMessage> getMessagePage() {
        return messagePage;
    }

    public void setMessagePage(List<PrivateMessage> messagePage) {
        this.messagePage = messagePage;
    }
}
