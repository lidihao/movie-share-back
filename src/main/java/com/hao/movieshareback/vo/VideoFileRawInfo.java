package com.hao.movieshareback.vo;

public class VideoFileRawInfo {
    private Long duration;
    private ScreenPicture screenPicture;

    public VideoFileRawInfo(Long duration, ScreenPicture screenPicture) {
        this.duration = duration;
        this.screenPicture = screenPicture;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public ScreenPicture getScreenPicture() {
        return screenPicture;
    }

    public void setScreenPicture(ScreenPicture screenPicture) {
        this.screenPicture = screenPicture;
    }
}
