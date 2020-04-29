package com.hao.movieshareback.vo;

public class TagNameCountMap {
    private String tagName;
    private Integer count;

    public TagNameCountMap() {
    }

    public TagNameCountMap(String tagName, Integer count) {
        this.tagName = tagName;
        this.count = count;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
