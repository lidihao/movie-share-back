package com.hao.movieshareback.vo;

import com.hao.movieshareback.model.Category;
import com.hao.movieshareback.model.Tag;
import com.hao.movieshareback.vo.auth.UserVo;

import java.util.Date;
import java.util.List;

public class VideoDetailVo {
    private String title;
    private Category category;
    private Date createTime;
    private String introduce;
    private List<Tag> tagList;
    private UserVo uploader;
    private Long videoPlayCount;
    private Long videoCommentCount;

    public VideoDetailVo() {
    }

    public VideoDetailVo(String title, Category category, Date createTime, String introduce, List<Tag> tagList, UserVo uploader, Long videoPlayCount, Long videoCommentCount) {
        this.title = title;
        this.category = category;
        this.createTime = createTime;
        this.introduce = introduce;
        this.tagList = tagList;
        this.uploader = uploader;
        this.videoPlayCount = videoPlayCount;
        this.videoCommentCount = videoCommentCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public UserVo getUploader() {
        return uploader;
    }

    public void setUploader(UserVo uploader) {
        this.uploader = uploader;
    }

    public Long getVideoPlayCount() {
        return videoPlayCount;
    }

    public void setVideoPlayCount(Long videoPlayCount) {
        this.videoPlayCount = videoPlayCount;
    }

    public Long getVideoCommentCount() {
        return videoCommentCount;
    }

    public void setVideoCommentCount(Long videoCommentCount) {
        this.videoCommentCount = videoCommentCount;
    }
}
