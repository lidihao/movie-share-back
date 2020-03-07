package com.hao.movieshareback.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hao.movieshareback.service.PrivateMessageService;
import com.hao.movieshareback.vo.MessageReceiver;
import com.hao.movieshareback.vo.PrivateMsgUserItemVo;
import com.hao.movieshareback.vo.ResultBody;
import com.hao.movieshareback.vo.XPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/privateMsg")
@RestController
public class PrivateMsgController {

    @Autowired
    private PrivateMessageService privateMessageService;

    @GetMapping("/userList")
    public ResultBody getPrivateMsgUserList(Integer pageNum,Integer pageSize,Integer userId){
        XPage<PrivateMsgUserItemVo> privateMsgUserItemVoXPage = privateMessageService.getPrivateMsgUserList(userId,pageNum,pageSize);
        return ResultBody.success(privateMsgUserItemVoXPage);
    }

    @GetMapping("/userItemDetail")
    public ResultBody getUserItemDetail(Integer userId,Integer friendId){
        return ResultBody.success(privateMessageService.getUserItemDetail(userId,friendId));
    }

    @GetMapping("/messageList")
    public ResultBody getMessageList(Integer userId,Integer friendId,Integer pageNum,Integer pageSize){
        return ResultBody.success(privateMessageService.getMessageList(userId,friendId,pageNum,pageSize));
    }

    @PostMapping("/sendMessage")
    public ResultBody sendMessage(@RequestBody MessageReceiver messageReceiver) throws JsonProcessingException {
        return ResultBody.success(privateMessageService.sendMessage(messageReceiver));
    }
}
