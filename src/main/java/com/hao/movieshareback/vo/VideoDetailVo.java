package com.hao.movieshareback.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hao.movieshareback.model.Category;
import com.hao.movieshareback.model.Picture;
import com.hao.movieshareback.model.Tag;
import com.hao.movieshareback.vo.auth.UserVo;

import java.util.Date;
import java.util.List;

public class VideoDetailVo {
    private Integer videoId;
    private String title;
    private Category category;
    private Date createTime;
    private String introduce;
    private List<Tag> tagList;
    private UserVo uploader;
    private Long videoPlayCount;
    private Long videoCommentCount;
    private Picture posterPicture;
    private Double rate;

    public VideoDetailVo() {
    }

    public VideoDetailVo(Integer videoId,String title, Category category, Date createTime, String introduce, List<Tag> tagList,
                         UserVo uploader, Long videoPlayCount, Long videoCommentCount,Picture posterPicture,Double rate) {
        this.videoId=videoId;
        this.title = title;
        this.category = category;
        this.createTime = createTime;
        this.introduce = introduce;
        this.tagList = tagList;
        this.uploader = uploader;
        this.videoPlayCount = videoPlayCount;
        this.videoCommentCount = videoCommentCount;
        this.posterPicture=posterPicture;
        this.rate=rate;
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

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
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

    public Picture getPosterPicture() {
        return posterPicture;
    }

    public void setPosterPicture(Picture posterPicture) {
        this.posterPicture = posterPicture;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
