package com.hao.movieshareback.model.task;

public enum TaskStatus {
    RUNNING("running"),PAUSE("pause");
    private String tag;
    TaskStatus(String tag){
        this.tag=tag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
