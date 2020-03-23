package com.hao.movieshareback.vo;

import com.hao.movieshareback.model.Category;

public class CategoryRecommendVo {
    private Category category;
    private XPage<VideoDetailVo> videoDetailVoXPage;

    public CategoryRecommendVo() {
    }

    public CategoryRecommendVo(Category category, XPage<VideoDetailVo> videoDetailVoXPage) {
        this.category = category;
        this.videoDetailVoXPage = videoDetailVoXPage;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public XPage<VideoDetailVo> getVideoDetailVoXPage() {
        return videoDetailVoXPage;
    }

    public void setVideoDetailVoXPage(XPage<VideoDetailVo> videoDetailVoXPage) {
        this.videoDetailVoXPage = videoDetailVoXPage;
    }
}
