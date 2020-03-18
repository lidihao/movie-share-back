package com.hao.movieshareback.controller;

import com.google.common.collect.Sets;
import com.hao.movieshareback.model.Menu;
import com.hao.movieshareback.service.MenuService;
import com.hao.movieshareback.service.RoleService;
import com.hao.movieshareback.vo.ResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequestMapping("/role")
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @PreAuthorize("@el.check('admin')")
    @GetMapping("/getAllRole")
    public ResultBody getAllRole(){
        return ResultBody.success(roleService.getAllRole());
    }

    @PreAuthorize("@el.check('admin')")
    @GetMapping("/getMenuTreeLeafByRoleId")
    public ResultBody getMenuTreeLeafByRoleId(Integer roleId){
        return ResultBody.success(menuService.getMenuTreeLeafByRoleIdNotRescurse(roleId));
    }

    @PreAuthorize("@el.check('admin')")
    @PostMapping("/updateMenuTree")
    public ResultBody updateMenuTree(Integer roleId, @RequestParam("menuTree") ArrayList<Integer> menuTree){
        Set<Integer> menuSet = Sets.newHashSet(menuTree);
        menuService.updateMenuTreeByRoleId(roleId,menuSet);
        return ResultBody.success();
    }
}
