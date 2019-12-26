package com.hao.movieshareback.dao;

import com.hao.movieshareback.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    /**
     * 新增用户
     * @param user
     * @return
     */
    int save (User user);

    User getUserByUserName(String userName);
}
