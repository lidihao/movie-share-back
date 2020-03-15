package com.hao.movieshareback.dao;

import com.hao.movieshareback.model.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MenuMapper {
    List<Menu> getMenuListByRoleId(Integer roleID);
    List<Menu> selectAllMenu();
    Menu selectMenuByMenuId(Integer menuId);
    void updateHasChild(boolean tag,Integer menuId);
    void save(Menu menu);
    Integer selectCountByMenuEngName(String menuEng);
    void updateMenu(Menu menu);
    Menu selectMenuByEngName(String menuEng);
    void deleteMenuById(Integer menuId);
    Integer selectMenuChildCount(Integer menuId);
    List<Menu> selectChildMenu(Integer menuId);
}
