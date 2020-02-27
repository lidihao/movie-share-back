package com.hao.movieshareback.vo;

import com.hao.movieshareback.vo.auth.UserVo;

import java.util.Date;
import java.util.List;

public class VideoApplyVo {
    private Integer videoApprovalId;
    private Integer videoId;
    private String title;
    private String posterUrl;
    private Integer approvalType;
    private UserVo userVo;
    private Date createdTime;
    private Integer categoryId;
    private String introduce;
    private String remark;
    private List<Integer> tagIdList;


    public VideoApplyVo(Integer videoApprovalId, Integer videoId,
                        String title, String posterUrl,Integer approvalType, UserVo userVo, Date createdTime,
                        Integer categoryId,String introduce,String remark,List<Integer> tagIdList) {
        this.videoApprovalId = videoApprovalId;
        this.videoId = videoId;
        this.title = title;
        this.posterUrl = posterUrl;
        this.approvalType = approvalType;
        this.userVo = userVo;
        this.createdTime = createdTime;
        this.categoryId=categoryId;
        this.introduce=introduce;
        this.remark=remark;
        this.tagIdList=tagIdList;
    }

    public Integer getVideoApprovalId() {
        return videoApprovalId;
    }

    public void setVideoApprovalId(Integer videoApprovalId) {
        this.videoApprovalId = videoApprovalId;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public UserVo getUserVo() {
        return userVo;
    }

    public void setUserVo(UserVo userVo) {
        this.userVo = userVo;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public List<Integer> getTagIdList() {
        return tagIdList;
    }

    public void setTagIdList(List<Integer> tagIdList) {
        this.tagIdList = tagIdList;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getApprovalType() {
        return approvalType;
    }

    public void setApprovalType(Integer approvalType) {
        this.approvalType = approvalType;
    }
}
