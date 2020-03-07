package com.hao.movieshareback.model;

public class UserSkin {
    private Integer userSkinId;
    private Integer pictureId;
    private Integer userId;

    public UserSkin() {
    }

    public UserSkin(Integer pictureId, Integer userId) {
        this.pictureId = pictureId;
        this.userId = userId;
    }

    public Integer getUserSkinId() {
        return userSkinId;
    }

    public void setUserSkinId(Integer userSkinId) {
        this.userSkinId = userSkinId;
    }

    public Integer getPictureId() {
        return pictureId;
    }

    public void setPictureId(Integer pictureId) {
        this.pictureId = pictureId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
