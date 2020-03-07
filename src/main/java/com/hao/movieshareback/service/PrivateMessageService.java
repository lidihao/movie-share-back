package com.hao.movieshareback.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hao.movieshareback.dao.PrivateMessageMapper;
import com.hao.movieshareback.model.PrivateMessage;
import com.hao.movieshareback.model.User;
import com.hao.movieshareback.model.UserMsgMapping;
import com.hao.movieshareback.model.bo.PrivateMsgUserItem;
import com.hao.movieshareback.utils.JsonConvert;
import com.hao.movieshareback.vo.*;
import com.hao.movieshareback.vo.auth.UserVo;
import com.hao.movieshareback.websocket.WebsocketIMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class PrivateMessageService {

    @Autowired
    private PrivateMessageMapper privateMessageMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private WebsocketIMessage websocketIMessage;

    public XPage<PrivateMsgUserItemVo> getPrivateMsgUserList(Integer userId,Integer pageNum,Integer pageSize){
        Page page = new Page(pageNum,pageSize);
        PageList<PrivateMsgUserItem> privateMsgUserItemPageList=privateMessageMapper.getPrivateMsgUserList(page,userId);
        PageList<PrivateMsgUserItemVo> privateMsgUserItemVos = new PageList<>();
        privateMsgUserItemVos.setPageInfo(privateMsgUserItemPageList.getPageInfo());
        privateMsgUserItemPageList.forEach(privateMsgUserItem -> {
            UserVo friendVo = userService.getUserVoByUserId(privateMsgUserItem.getFriendId());
            PrivateMessage privateMessage = privateMessageMapper.getPrivateMsgByMsgId(privateMsgUserItem.getMessageId());
            Integer unReadCount = privateMessageMapper.getUnReadPrivateMsg(userId,privateMsgUserItem.getFriendId());
            privateMsgUserItemVos.add(new PrivateMsgUserItemVo(friendVo,privateMessage,unReadCount));
        });
        return XPage.wrap(privateMsgUserItemVos);
    }

    public MessageListVo getMessageList(Integer userId,Integer friendId,Integer pageNum,Integer pageSize){
        Page page = new Page(pageNum,pageSize);
        UserVo userVo = userService.getUserVoByUserId(userId);
        UserVo friendVo = userService.getUserVoByUserId(friendId);
        PageList<PrivateMessage> messageList = privateMessageMapper.getMessageList(page,userId,friendId);
        return new MessageListVo(userVo,friendVo,XPage.wrap(messageList));
    }

    public PrivateMsgUserItemVo getUserItemDetail(Integer userId,Integer friendId){
        if (userId==friendId){
            return null;
        }
        UserVo userVo = userService.getUserVoByUserId(friendId);
        Integer unReadCount = privateMessageMapper.getUnReadPrivateMsg(userId,friendId);
        PrivateMsgUserItem privateMsgUserItem = privateMessageMapper.getUserItemDetail(userId,friendId);
        PrivateMessage privateMessage = null;
        if (privateMsgUserItem!=null) {
            privateMessage=privateMessageMapper.getPrivateMsgByMsgId(privateMsgUserItem.getMessageId());
        }
        return new PrivateMsgUserItemVo(userVo,privateMessage,unReadCount);
    }

    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public PrivateMessage sendMessage(MessageReceiver messageReceiver) throws JsonProcessingException {
        PrivateMessage privateMessage = new PrivateMessage(messageReceiver.getSenderId(),messageReceiver.getReceiverId(),messageReceiver.getMessageContent(),new Date());
        privateMessageMapper.save(privateMessage);
        UserMsgMapping userMsgMapping = new UserMsgMapping(messageReceiver.getSenderId(),messageReceiver.getReceiverId(),new Date(),privateMessage.getMessageId());
        UserMsgMapping friendMsgMapping = new UserMsgMapping(messageReceiver.getReceiverId(),messageReceiver.getSenderId(),new Date(),privateMessage.getMessageId());
        privateMessageMapper.saveUserMapping(userMsgMapping);
        privateMessageMapper.saveUserMapping(friendMsgMapping);
        websocketIMessage.sendOneMessage(messageReceiver.getReceiverId(), JsonConvert.objToJson(privateMessage));
        return privateMessage;
    }
}
