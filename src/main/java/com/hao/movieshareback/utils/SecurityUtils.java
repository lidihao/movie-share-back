package com.hao.movieshareback.utils;

import cn.hutool.json.JSONObject;
import com.hao.movieshareback.exception.LoginException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 获取当前登录的用户
 * @author Zheng Jie
 * @date 2019-01-17
 */
public class SecurityUtils {

    public static UserDetails getUserDetails() {
        UserDetails userDetails;
        try {
            userDetails = (UserDetails) org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new LoginException("登录状态过期");
        }
        return userDetails;
    }

    /**
     * 获取系统用户名称
     * @return 系统用户名称
     */
    public static String getUsername(){
        UserDetails userDetails = getUserDetails();
        if (userDetails!=null) {
            return userDetails.getUsername();
        }else {
            return "AnonymousAccess";
        }
    }

    public static boolean isLogin(){
        try {
            return getUserDetails()!=null;
        }catch (Exception e){
            return false;
        }
    }
}