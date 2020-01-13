package com.hao.movieshareback.vo;

public class ScreenPicture {
    private Integer height;
    private Integer width;
    private Long size;
    private String path;
    private String format;
    private String rotate;

    public ScreenPicture(Integer height, Integer width, Long size, String path, String format, String rotate) {
        this.height = height;
        this.width = width;
        this.size = size;
        this.path = path;
        this.format = format;
        this.rotate = rotate;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getRotate() {
        return rotate;
    }

    public void setRotate(String rotate) {
        this.rotate = rotate;
    }
}
