package com.hao.movieshareback.model;

/**
 * CREATE TABLE `picture`  (
 *     `picture_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
 *     `filename` varchar(255) NOT NULL COMMENT '图片名称',
 *     `height` int NOT NULL COMMENT '图片高度',
 *     `size` bigint NOT NULL COMMENT '图片大小',
 *     `url` varchar(255) NOT NULL COMMENT '图片地址',
 *     `width` int NOT NULL COMMENT '图片宽度',
 *     `created_time` DATETIME    COMMENT '创建时间' ,
 *     `created_by` VARCHAR(32)    COMMENT '创建时间',
 *     `updated_time` DATETIME    COMMENT '更新时间' ,
 *     `updated_by` VARCHAR(32)    COMMENT '更新人' ,
 *     `is_delete` int(1) NOT NULL  DEFAULT 0 COMMENT '是否删除',
 *     PRIMARY KEY (`picture_id`),
 *     INDEX (is_delete)
 * )COMMENT '图片' ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1000;
 */
public class Picture extends BaseModel{
    private Integer pictureId;
    private String fileName;
    private Integer height;
    private Long size;
    private Integer width;
    private String url;

    public Picture(String fileName, Integer height, Long size, Integer width, String url) {
        this.fileName = fileName;
        this.height = height;
        this.size = size;
        this.width = width;
        this.url = url;
    }

    public Picture() {
    }

    public Integer getPictureId() {
        return pictureId;
    }

    public void setPictureId(Integer pictureId) {
        this.pictureId = pictureId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
