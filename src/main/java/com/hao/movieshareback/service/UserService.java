package com.hao.movieshareback.service;

import com.hao.movieshareback.dao.UserMapper;
import com.hao.movieshareback.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User getUserByUserName(String userName){
        return userMapper.getUserByUserName(userName);
    }
}
