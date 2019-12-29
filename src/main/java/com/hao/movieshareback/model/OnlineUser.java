package com.hao.movieshareback.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Zheng Jie
 */

public class OnlineUser implements Serializable {

    private String userName;

    private String browser;

    private String ip;

    private String key;

    private Date loginTime;

    public OnlineUser() {
    }

    public OnlineUser(String userName, String browser, String ip, String key, Date loginTime) {
        this.userName = userName;
        this.browser = browser;
        this.ip = ip;
        this.key = key;
        this.loginTime = loginTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
}
