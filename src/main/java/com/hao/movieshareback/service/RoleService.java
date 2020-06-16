package com.hao.movieshareback.service;

import com.hao.movieshareback.dao.RoleMapper;
import com.hao.movieshareback.model.BaseModel;
import com.hao.movieshareback.model.Role;
import com.hao.movieshareback.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;

    public List<Role> getRolesByUserName(String userName){
        return roleMapper.selectRolesByUserName(userName);
    }

    public Role getRoleByRoleName(String roleName){
        return roleMapper.selectRoleByRoleName(roleName);
    }

    public List<Role> getAllRole(){
        return roleMapper.selectAllRole();
    }

    public void addRole(Role role){
        BaseModel.setUpdated(role, SecurityUtils.getUsername(),new Date());
        BaseModel.setNewCreate(role,SecurityUtils.getUsername(),new Date());
        roleMapper.save(role);
    }

    public void updateRole(Role role){
        roleMapper.updateDesc(role.getRoleDes(),SecurityUtils.getUsername(),role.getRoleId());
    }

    @Transactional
    public void deleteRole(Integer roleId){
        roleMapper.deleteAllUserRoleMapping(roleId);
        roleMapper.deleteAllMenuRoleMapping(roleId);
        roleMapper.deleteRole(roleId);
    }
}
