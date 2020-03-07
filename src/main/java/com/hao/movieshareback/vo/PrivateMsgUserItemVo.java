package com.hao.movieshareback.vo;

import com.hao.movieshareback.model.PrivateMessage;
import com.hao.movieshareback.vo.auth.UserVo;

public class PrivateMsgUserItemVo {
    private UserVo friend;
    private PrivateMessage lastMsg;
    private Integer unReadMsgCount;

    public PrivateMsgUserItemVo() {
    }

    public PrivateMsgUserItemVo(UserVo friend, PrivateMessage lastMsg, Integer unReadMsgCount) {
        this.friend = friend;
        this.lastMsg = lastMsg;
        this.unReadMsgCount = unReadMsgCount;
    }

    public UserVo getFriend() {
        return friend;
    }

    public void setFriend(UserVo friend) {
        this.friend = friend;
    }

    public PrivateMessage getLastMsg() {
        return lastMsg;
    }

    public void setLastMsg(PrivateMessage lastMsg) {
        this.lastMsg = lastMsg;
    }

    public Integer getUnReadMsgCount() {
        return unReadMsgCount;
    }

    public void setUnReadMsgCount(Integer unReadMsgCount) {
        this.unReadMsgCount = unReadMsgCount;
    }
}
