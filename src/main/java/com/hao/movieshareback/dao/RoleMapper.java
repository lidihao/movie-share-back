package com.hao.movieshareback.dao;

import com.hao.movieshareback.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RoleMapper {
    public List<Role> selectRolesByUserName(String userName);
    public Role selectRoleByRoleName(String roleName);
}
