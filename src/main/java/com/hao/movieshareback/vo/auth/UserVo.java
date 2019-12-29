package com.hao.movieshareback.vo.auth;

import com.hao.movieshareback.vo.MenuVo;

import java.io.Serializable;
import java.util.List;

public class UserVo implements Serializable {
    private String userName;
    private String avatarUrl;
    private String email;
    private List<MenuVo> menus;

    public UserVo(String userName, String avatarUrl, String email,List<MenuVo> menus) {
        this.userName = userName;
        this.avatarUrl = avatarUrl;
        this.email = email;
        this.menus=menus;
    }
    public String getUserName() {
        return userName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getEmail() {
        return email;
    }

    public List<MenuVo> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuVo> menus) {
        this.menus = menus;
    }
}
