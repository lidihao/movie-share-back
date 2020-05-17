package com.hao.movieshareback.controller;

import com.hao.movieshareback.model.type.MessageType;
import com.hao.movieshareback.service.SystemMessageService;
import com.hao.movieshareback.vo.MessageTypeKv;
import com.hao.movieshareback.vo.ResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/systemMessage")
@RestController
public class SystemMessageController {

    @Autowired
    private SystemMessageService systemMessageService;

    @GetMapping("/list")
    public ResultBody getSystemList(String messageType,Integer userId,Integer pageNum,Integer pageSize){
        return ResultBody.success(systemMessageService.listSystemMessageByCondition(messageType, userId, pageNum, pageSize));
    }

    @GetMapping("/getMessageType")
    public ResultBody getMessageTypeList(){
        MessageType[] messageTypes = MessageType.values();
        List<MessageTypeKv> messageTypeKvs = new ArrayList<>(messageTypes.length);
        for (MessageType messageType:messageTypes){
            messageTypeKvs.add(new MessageTypeKv(messageType.getDesc(),messageType.getMessageType()));
        }
        return ResultBody.success(messageTypeKvs);
    }
}
