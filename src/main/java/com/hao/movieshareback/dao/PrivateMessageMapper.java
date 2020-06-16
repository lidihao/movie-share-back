package com.hao.movieshareback.dao;

import com.hao.movieshareback.model.PrivateMessage;
import com.hao.movieshareback.model.UserMsgMapping;
import com.hao.movieshareback.model.bo.PrivateMsgUserItem;
import com.hao.movieshareback.vo.Page;
import com.hao.movieshareback.vo.PageList;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface PrivateMessageMapper {
    void save(PrivateMessage privateMessage);
    PageList<PrivateMsgUserItem> getPrivateMsgUserList(Page page, Integer userId);
    PrivateMessage getPrivateMsgByMsgId(Integer messageId);
    Integer getUnReadPrivateMsg(Integer userId,Integer friendId);
    List<PrivateMessage> getMessageList(Integer userId, Integer friendId);
    void saveUserMapping(UserMsgMapping userMsgMapping);
    PrivateMsgUserItem getUserItemDetail(Integer userId,Integer friendId);
}
