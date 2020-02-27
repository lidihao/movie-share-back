package com.hao.movieshareback.vo;

public class ApprovalStatusVo {
    private Integer tag;
    private String desc;

    public ApprovalStatusVo() {
    }

    public ApprovalStatusVo(Integer tag, String desc) {
        this.tag = tag;
        this.desc = desc;
    }

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
