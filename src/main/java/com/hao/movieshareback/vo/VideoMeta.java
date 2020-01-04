package com.hao.movieshareback.vo;

import java.util.List;

public class VideoMeta {
    private String title;
    private String introduce;
    private List<Integer> tagList;
    private Integer categoryId;

    private Integer pictureId;
    private List<Integer> videoFileId;

    public VideoMeta() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public List<Integer> getTagList() {
        return tagList;
    }

    public void setTagList(List<Integer> tagList) {
        this.tagList = tagList;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getPictureId() {
        return pictureId;
    }

    public void setPictureId(Integer pictureId) {
        this.pictureId = pictureId;
    }

    public List<Integer> getVideoFileId() {
        return videoFileId;
    }

    public void setVideoFileId(List<Integer> videoFileId) {
        this.videoFileId = videoFileId;
    }
}
