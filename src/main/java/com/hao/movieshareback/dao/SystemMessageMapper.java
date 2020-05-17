package com.hao.movieshareback.dao;

import com.hao.movieshareback.model.SystemMessage;
import com.hao.movieshareback.vo.Page;
import com.hao.movieshareback.vo.PageList;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SystemMessageMapper {
    void save(SystemMessage systemMessage);
    PageList<SystemMessage> getSystemMessageByCondition(Page page,String messageType, Integer userId);
}
