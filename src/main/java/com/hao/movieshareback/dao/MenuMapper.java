package com.hao.movieshareback.dao;

import com.hao.movieshareback.model.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MenuMapper {
    List<Menu> getMenuListByRoleId(Integer roleID);
}
