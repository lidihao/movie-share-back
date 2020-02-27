package com.hao.movieshareback.vo.auth;

import com.hao.movieshareback.vo.MenuVo;

import java.io.Serializable;
import java.util.List;

public class UserVo implements Serializable {
    private Integer userId;
    private String userName;
    private String avatarUrl;
    private String introduce;
    private String email;
    private List<MenuVo> menus;

    public UserVo() {
    }

    public UserVo(Integer userId, String userName, String avatarUrl, String email, List<MenuVo> menus) {
        this.userId = userId;
        this.userName = userName;
        this.avatarUrl = avatarUrl;
        this.email = email;
        this.menus = menus;
    }

    public UserVo(Integer userId, String userName, String avatarUrl, String introduce, String email, List<MenuVo> menus) {
        this.userId = userId;
        this.userName = userName;
        this.avatarUrl = avatarUrl;
        this.introduce = introduce;
        this.email = email;
        this.menus = menus;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setEmail(String email) {
        this.email = email;
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
