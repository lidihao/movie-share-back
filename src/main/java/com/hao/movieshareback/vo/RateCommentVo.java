package com.hao.movieshareback.vo;

import com.hao.movieshareback.vo.auth.UserVo;

import java.util.Date;

public class RateCommentVo extends VideoCommentVo {
    private Double rate;

    public RateCommentVo() {
    }

    public RateCommentVo(Integer commentId, String commentContent, Long commentUp, Long commentDown, Integer videoId, UserVo commentUser, Date createTime, Double rate) {
        super(commentId, commentContent, commentUp, commentDown, videoId, commentUser, createTime);
        this.rate = rate;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
