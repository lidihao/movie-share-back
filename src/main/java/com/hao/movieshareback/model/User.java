package com.hao.movieshareback.model;

import java.util.Date;

public class User extends BaseModel {
    private Integer userId;
    private String userName;
    private String password;
    private String salt;
    private String mail;
    private String introduce;
    private Integer avatarPicId;
    private Date lastPasswordResetDate;

    public User() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Integer getAvatarPicId() {
        return avatarPicId;
    }

    public void setAvatarPicId(Integer avatarPicId) {
        this.avatarPicId = avatarPicId;
    }

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("userId=").append(userId);
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", salt='").append(salt).append('\'');
        sb.append(", mail='").append(mail).append('\'');
        sb.append(", introduce='").append(introduce).append('\'');
        sb.append(", avatarPicId=").append(avatarPicId);
        sb.append(", lastPasswordResetDate=").append(lastPasswordResetDate);
        sb.append('}');
        return sb.toString();
    }
}
