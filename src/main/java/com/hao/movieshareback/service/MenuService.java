package com.hao.movieshareback.service;

import com.google.common.collect.Sets;
import com.hao.movieshareback.dao.MenuMapper;
import com.hao.movieshareback.model.BaseModel;
import com.hao.movieshareback.model.Menu;
import com.hao.movieshareback.model.Role;
import com.hao.movieshareback.utils.SecurityUtils;
import com.hao.movieshareback.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RoleService roleService;

    public List<MenuVo> createMenuVoList(String userName){
        List<Menu> menuList = this.getMenuListByUserName(userName);
        return generateMenuTree(menuList);
    }

    public List<Menu> getMenuListByUserName(String userName){
        List<Role> roleList = roleService.getRolesByUserName(userName);
        return roleList.stream().flatMap(role -> menuMapper.getMenuListByRoleId(role.getRoleId()).stream())
                .collect(Collectors.toList());
    }

    public List<Menu> getMenuTreeLeafByRoleIdNotRescurse(Integer roleId){
        return menuMapper.getMenuTreeLeafByRoleId(roleId);
    }

    public Menu getMenuByMenuId(Integer menuId){
        return menuMapper.selectMenuByMenuId(menuId);
    }

    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public void addMenu(Menu menu){
        if (menuMapper.selectCountByMenuEngName(menu.getMenuEng())!=0){
            throw new RuntimeException("menuEngName duplicate");
        }
        String userName = SecurityUtils.getUsername();
        BaseModel.setNewCreate(menu,userName,new Date());
        BaseModel.setUpdated(menu,userName,new Date());
        menuMapper.save(menu);
        if (!menu.getParentMenuId().equals(Menu.ROOT_TAG)){
            menuMapper.updateHasChild(true,menu.getParentMenuId());
        }
    }

    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public void updateMenuTreeByRoleId(Integer roleId, Set<Integer> menuTreeId){
        List<Menu> menuList = menuMapper.getMenuListByRoleId(roleId);
        Set<Integer> menuSet = new HashSet<>();
        if (menuList!=null&&!menuList.isEmpty()){
            menuList.forEach(menu -> {
                menuSet.add(menu.getMenuId());
            });
        }

        Set<Integer> intersetionSet= Sets.intersection(menuSet,menuTreeId);

        Set<Integer> deleteSet = Sets.difference(menuSet,intersetionSet);
        Set<Integer> addSet = Sets.difference(menuTreeId,intersetionSet);

        deleteSet.forEach(menuId->{
            menuMapper.deleteRoleMenuMap(roleId,menuId);
        });

        addSet.forEach(menuId->{
            menuMapper.addRoleMenuMap(roleId,menuId);
        });
    }

    public List<MenuVo> getAllMenu(){
        List<Menu> menuList = menuMapper.selectAllMenu();
        return generateMenuTree(menuList);
    }


    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public void updateMenu(Menu menu){
        Menu dup = menuMapper.selectMenuByEngName(menu.getMenuEng());
        if (dup!=null&&!dup.getMenuId().equals(menu.getMenuId())){
            throw new RuntimeException("menuEngName duplicate");
        }

        String userName = SecurityUtils.getUsername();
        BaseModel.setUpdated(menu,userName,new Date());
        menuMapper.updateMenu(menu);
        if (!menu.getParentMenuId().equals(Menu.ROOT_TAG)){
            menuMapper.updateHasChild(true,menu.getParentMenuId());
        }
    }

    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public void deleteMenu(Integer menuId){
        Menu menu = menuMapper.selectMenuByMenuId(menuId);
        deleteMenuRecurse(menuId);
        if (!menu.getParentMenuId().equals(Menu.ROOT_TAG)&&
                menuMapper.selectMenuChildCount(menu.getParentMenuId())==0){
            menuMapper.updateHasChild(false,menu.getParentMenuId());
        }
    }

    private void deleteMenuRecurse(Integer menuId){
        List<Menu> menuList = menuMapper.selectChildMenu(menuId);
        menuMapper.deleteMenuById(menuId);
        if (menuList!=null&&!menuList.isEmpty()){
            menuList.forEach(menu -> {
                deleteMenuRecurse(menu.getMenuId());
            });
        }
    }

    public List<MenuVo> createMenuVoListByRoleName(String roleName){
        Role role = roleService.getRoleByRoleName(roleName);
        List<Menu> menuList = menuMapper.getMenuListByRoleId(role.getRoleId());
        return generateMenuTree(menuList);
    }

    public List<MenuVo> getChildMenuByParentId(Integer parentId,List<Menu> menuList){
        return menuList.stream().filter(menu -> menu.getParentMenuId().equals(parentId))
                //排序
                .sorted(Comparator.comparing(Menu::getSort))
                //转换成MenuVo
                .map(menu -> new MenuVo(menu.getMenuId(),menu.getMenuName(),menu.getMenuEng(),
                        menu.getMenuDes(),menu.getMenuUrl(),menu.isHasChild()))
                .collect(Collectors.toList());
    }

    private List<MenuVo> generateMenuTree(List<Menu> menuList){
        List<MenuVo> roots = this.getChildMenuByParentId(Menu.ROOT_TAG,menuList);
        for (MenuVo menuVo:roots){
            populateMenuChildren(menuVo,menuList);
        }
        return roots;
    }

    private void populateMenuChildren(MenuVo menuVo,List<Menu> menuList){
        if (menuVo.isHasChild()){
            List<MenuVo> childMenuList = getChildMenuByParentId(menuVo.getMenuId(),menuList);
            menuVo.setChildren(childMenuList);
            for (MenuVo childMenu:childMenuList){
                populateMenuChildren(childMenu,menuList);
            }
        }
    }

}
