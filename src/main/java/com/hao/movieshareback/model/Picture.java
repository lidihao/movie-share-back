package com.hao.movieshareback.model;

/**
 * CREATE TABLE `picture`  (
 *     `picture_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
 *     `filename` varchar(255) NOT NULL COMMENT '图片名称',
 *     `height` bigint NOT NULL COMMENT '图片高度',
 *     `size` bigint NOT NULL COMMENT '图片大小',
 *     `url` varchar(255) NOT NULL COMMENT '图片地址',
 *     `width` bigint NOT NULL COMMENT '图片宽度',
 *     `created_time` DATETIME    COMMENT '创建时间' ,
 *     `created_by` VARCHAR(32)    COMMENT '创建时间',
 *     `updated_time` DATETIME    COMMENT '更新时间' ,
 *     `updated_by` VARCHAR(32)    COMMENT '更新人' ,
 *     `is_delete` int(1) NOT NULL  DEFAULT 0 COMMENT '是否删除',
 *     PRIMARY KEY (`picture_id`),
 *     INDEX (is_delete)
 * )COMMENT '图片' ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1000;
 */
public class Picture {
}
