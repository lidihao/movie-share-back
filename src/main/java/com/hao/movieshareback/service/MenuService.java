package com.hao.movieshareback.service;

import com.hao.movieshareback.dao.MenuMapper;
import com.hao.movieshareback.model.Menu;
import com.hao.movieshareback.model.Role;
import com.hao.movieshareback.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
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
            menuVo.setMenuVoList(childMenuList);
            for (MenuVo childMenu:childMenuList){
                populateMenuChildren(childMenu,menuList);
            }
        }
    }
}
