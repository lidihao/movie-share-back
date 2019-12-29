package com.hao.movieshareback.dao;

import com.hao.movieshareback.model.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PermissionMapper {
    List<Permission> getPermissionsByRoleId(Integer roleId);
    int save(Permission permission);
}
