package com.hao.movieshareback.model.bo;

public class PersonRecommendKey {
    private Integer videoId;
    private String recommendReason;
    private String params;

    public PersonRecommendKey() {
    }

    public PersonRecommendKey(Integer videoId, String recommendReason) {
        this.videoId = videoId;
        this.recommendReason = recommendReason;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getRecommendReason() {
        return recommendReason;
    }

    public void setRecommendReason(String recommendReason) {
        this.recommendReason = recommendReason;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
