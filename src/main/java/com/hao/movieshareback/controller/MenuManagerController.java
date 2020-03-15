package com.hao.movieshareback.controller;

import com.hao.movieshareback.model.Menu;
import com.hao.movieshareback.service.MenuService;
import com.hao.movieshareback.vo.ResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menuManager")
public class MenuManagerController {

    @Autowired
    private MenuService menuService;


    @PreAuthorize("@el.check('admin')")
    @RequestMapping("/getAllMenu")
    public ResultBody getAllMenu(){
        return ResultBody.success(menuService.getAllMenu());
    }

    @RequestMapping("/getMenuDetail")
    public ResultBody getmenuDetail(Integer menuId){
        return ResultBody.success(menuService.getMenuByMenuId(menuId));
    }

    @PreAuthorize("@el.check('admin')")
    @PostMapping("/addMenu")
    public ResultBody addMenu(@RequestBody Menu menu){
        try {
            menuService.addMenu(menu);
            return ResultBody.success();
        }catch (Exception e){
            return ResultBody.error(e.getMessage());
        }
    }

    @PreAuthorize("@el.check('admin')")
    @PostMapping("/updateMenu")
    public ResultBody updateMenu(@RequestBody Menu menu){
        try {
            menuService.updateMenu(menu);
            return ResultBody.success();
        }catch (Exception e){
            return ResultBody.error(e.getMessage());
        }
    }

    @PreAuthorize("@el.check(admin)")
    @PostMapping("/deleteMenu")
    public ResultBody deleteMenu( Integer menuId){
        try {
            menuService.deleteMenu(menuId);
            return ResultBody.success();
        }catch (Exception e){
            return ResultBody.error(e.getMessage());
        }
    }
}
