package com.hao.movieshareback.model;

public class FavoriteVideo extends BaseModel {
    private Integer favoriteVideoId;
    private Integer videoId;
    private Integer userId;

    public FavoriteVideo() {
    }

    public FavoriteVideo(Integer favoriteVideoId, Integer videoId, Integer userId) {
        this.favoriteVideoId = favoriteVideoId;
        this.videoId = videoId;
        this.userId = userId;
    }

    public Integer getFavoriteVideoId() {
        return favoriteVideoId;
    }

    public void setFavoriteVideoId(Integer favoriteVideoId) {
        this.favoriteVideoId = favoriteVideoId;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
