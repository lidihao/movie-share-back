package com.hao.movieshareback.model;

/**
 *  `category_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
 *     `category_name` VARCHAR(100) NOT NULL COMMENT '类别名',
 *     `category_des` VARCHAR(200) NOT NULL COMMENT '描述',
 *     `created_time` DATETIME    COMMENT '创建时间' ,
 *     `created_by` VARCHAR(32)    COMMENT '创建时间',
 *     `updated_time` DATETIME    COMMENT '更新时间' ,
 *     `updated_by` VARCHAR(32)    COMMENT '更新人' ,
 *     `is_delete` int(1) NOT NULL  DEFAULT 0 COMMENT '是否删除',
 *     PRIMARY KEY (category_id),
 *     INDEX (is_delete)
 */
public class Category extends BaseModel{
    private Integer categoryId;
    private String categoryName;
    private String categoryDes;

    public Category(Integer categoryId, String categoryName, String categoryDes) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryDes = categoryDes;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDes() {
        return categoryDes;
    }

    public void setCategoryDes(String categoryDes) {
        this.categoryDes = categoryDes;
    }
}
