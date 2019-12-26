package com.hao.movieshareback.service;

import com.hao.movieshareback.dao.RoleMapper;
import com.hao.movieshareback.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;

    public List<Role> getRolesByUserName(String userName){
        return roleMapper.selectRolesByUserName(userName);
    }
}
