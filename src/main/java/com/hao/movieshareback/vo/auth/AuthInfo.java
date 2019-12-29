package com.hao.movieshareback.vo.auth;

import java.io.Serializable;

public class AuthInfo implements Serializable {

    private final String token;

    private final UserVo user;

    public AuthInfo(String token, UserVo user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public UserVo getUser() {
        return user;
    }
}