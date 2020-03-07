package com.hao.movieshareback.dao;

import com.hao.movieshareback.model.PrivateMessage;
import com.hao.movieshareback.model.UserMsgMapping;
import com.hao.movieshareback.model.bo.PrivateMsgUserItem;
import com.hao.movieshareback.vo.Page;
import com.hao.movieshareback.vo.PageList;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface PrivateMessageMapper {
    void save(PrivateMessage privateMessage);
    PageList<PrivateMsgUserItem> getPrivateMsgUserList(Page page, Integer userId);
    PrivateMessage getPrivateMsgByMsgId(Integer messageId);
    Integer getUnReadPrivateMsg(Integer userId,Integer friendId);
    PageList<PrivateMessage> getMessageList(Page page,Integer userId,Integer friendId);
    void saveUserMapping(UserMsgMapping userMsgMapping);
    PrivateMsgUserItem getUserItemDetail(Integer userId,Integer friendId);
}
