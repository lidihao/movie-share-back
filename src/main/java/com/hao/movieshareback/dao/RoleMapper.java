package com.hao.movieshareback.dao;

import com.hao.movieshareback.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RoleMapper {
    public void save(Role role);
    public List<Role> selectRolesByUserName(String userName);
    public Role selectRoleByRoleName(String roleName);
    public void addUserRole(Integer userId,Integer roleId);
    public List<Role> selectAllRole();
    public void deleteUserRoleMapping(Integer userId,Integer roleId);
    public void updateDesc(String roleDes,String updateBy,Integer roleId);
    public void deleteAllUserRoleMapping(Integer roleId);
    public void deleteAllMenuRoleMapping(Integer roleId);
    public void deleteRole(Integer roleId);
}
