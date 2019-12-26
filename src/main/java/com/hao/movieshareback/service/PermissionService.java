package com.hao.movieshareback.service;

import com.hao.movieshareback.dao.PermissionMapper;
import com.hao.movieshareback.model.Permission;
import com.hao.movieshareback.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionService {

    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionMapper permissionMapper;

    public List<Permission> getPermissionByUserName(String userName){
        List<Role> roleList = roleService.getRolesByUserName(userName);
        return roleList.stream().flatMap(role -> permissionMapper.getPermissionsByRoleId(role.getRoleId()).stream()).
                collect(Collectors.toList());
    }
}
