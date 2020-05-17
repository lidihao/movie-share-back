package com.hao.movieshareback.model.bo;

import com.hao.movieshareback.vo.VideoDetailVo;

public class PersonRecommendResult {
    private VideoDetailVo videoDetailVo;
    private String recommendResult;
    private String params;
    private Double recommendRate;

    public PersonRecommendResult() {
    }

    public VideoDetailVo getVideoDetailVo() {
        return videoDetailVo;
    }

    public void setVideoDetailVo(VideoDetailVo videoDetailVo) {
        this.videoDetailVo = videoDetailVo;
    }

    public String getRecommendResult() {
        return recommendResult;
    }

    public void setRecommendResult(String recommendResult) {
        this.recommendResult = recommendResult;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Double getRecommendRate() {
        return recommendRate;
    }

    public void setRecommendRate(Double recommendRate) {
        this.recommendRate = recommendRate;
    }
}
