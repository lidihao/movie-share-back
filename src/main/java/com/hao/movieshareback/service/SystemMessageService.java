package com.hao.movieshareback.service;

import com.hao.movieshareback.dao.SystemMessageMapper;
import com.hao.movieshareback.model.SystemMessage;
import com.hao.movieshareback.vo.Page;
import com.hao.movieshareback.vo.PageList;
import com.hao.movieshareback.vo.XPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemMessageService {

    @Autowired
    private SystemMessageMapper systemMessageMapper;

    public XPage<SystemMessage> listSystemMessageByCondition(String messageType,Integer userId,Integer pageNum,Integer pageSize){
        Page page = new Page(pageNum,pageSize);
        PageList<SystemMessage> pageList = systemMessageMapper.getSystemMessageByCondition(page,messageType,userId);
        return XPage.wrap(pageList);
    }
}
