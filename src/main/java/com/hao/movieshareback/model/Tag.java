package com.hao.movieshareback.model;

/**
 * `tag_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
 *     `tag_name` VARCHAR(100) NOT NULL COMMENT '标签名称',
 *     `tag_desc` VARCHAR(200) NOT NULL COMMENT '标签描述',
 *     `created_time` DATETIME    COMMENT '创建时间' ,
 *     `created_by` VARCHAR(32)    COMMENT '创建时间',
 *     `updated_time` DATETIME    COMMENT '更新时间' ,
 *     `updated_by` VARCHAR(32)    COMMENT '更新人' ,
 *     `is_delete` int(1) NOT NULL  DEFAULT 0 COMMENT '是否删除',
 *     PRIMARY KEY (tag_id),
 *     INDEX (is_delete)
 */
public class Tag extends BaseModel{
    private Integer tagId;
    private String tagName;
    private String tagDesc;

    public Tag(Integer tagId, String tagName, String tagDesc) {
        this.tagId = tagId;
        this.tagName = tagName;
        this.tagDesc = tagDesc;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagDesc() {
        return tagDesc;
    }

    public void setTagDesc(String tagDesc) {
        this.tagDesc = tagDesc;
    }
}
