package com.hao.movieshareback.vo;

public class ImgResult {
    private String img;
    private String uuid;

    public ImgResult(String img, String uuid) {
        this.img = img;
        this.uuid = uuid;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ImgResult{");
        sb.append("img='").append(img).append('\'');
        sb.append(", uuid='").append(uuid).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
