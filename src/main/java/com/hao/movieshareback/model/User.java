package com.hao.movieshareback.model;

import java.util.Date;

public class User extends BaseModel {
    private Integer userId;
    private String userName;
    private String password;
    private String salt;
    private String email;
    private String introduce;
    private Integer avatarPicId;
    private Integer userSkinId;
    private boolean hasActive;
    private Date lastPasswordResetDate;

    public User() {
    }

    public User(String userName, String password, String salt, String email) {
        this.userName = userName;
        this.password = password;
        this.salt = salt;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIntroduce() {
        return introduce;
    }

    public boolean isHasActive() {
        return hasActive;
    }

    public void setHasActive(boolean hasActive) {
        this.hasActive = hasActive;
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

    public Integer getUserSkinId() {
        return userSkinId;
    }

    public void setUserSkinId(Integer userSkinId) {
        this.userSkinId = userSkinId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("userId=").append(userId);
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", salt='").append(salt).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", introduce='").append(introduce).append('\'');
        sb.append(", avatarPicId=").append(avatarPicId);
        sb.append(", hasActive=").append(hasActive);
        sb.append(", lastPasswordResetDate=").append(lastPasswordResetDate);
        sb.append('}');
        return sb.toString();
    }
}
