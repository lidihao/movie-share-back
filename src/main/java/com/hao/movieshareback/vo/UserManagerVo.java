package com.hao.movieshareback.vo;

import com.hao.movieshareback.model.Role;
import com.hao.movieshareback.model.User;

import java.util.List;

public class UserManagerVo {
    private User user;
    private List<Role> roleList;


    public UserManagerVo() {
    }

    public UserManagerVo(User user, List<Role> roleList) {
        this.user = user;
        this.roleList = roleList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
