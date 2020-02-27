create table `user`(
    `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_name` VARCHAR(100) NOT NULL UNIQUE COMMENT '用户名',
    `password` CHAR(128) NOT NULL COMMENT '密码',
    `salt` CHAR(128) NOT NULL COMMENT '盐值',
    `email` VARCHAR(100) NOT NULL UNIQUE COMMENT '邮箱',
    `introduce` VARCHAR(200) NOT NULL DEFAULT '' COMMENT '简介',
    `avatar_pic_id` int(11) NOT NULL DEFAULT 1000 COMMENT '头像',
    `has_active` BIT NOT NULL DEFAULT 0 COMMENT '是否激活账号',
    `last_password_reset_date` DATETIME COMMENT '密码修改时间',
    `created_time` DATETIME    COMMENT '创建时间' ,
    `created_by` VARCHAR(32)    COMMENT '创建时间',
    `updated_time` DATETIME    COMMENT '更新时间' ,
    `updated_by` VARCHAR(32)    COMMENT '更新人' ,
    `is_delete` BIT NOT NULL  DEFAULT 0 COMMENT '是否删除',
    PRIMARY KEY (user_id),
    INDEX (is_delete)
)COMMENT '用户表' ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1000;

create table `role`(
    `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `role_name` VARCHAR(100) NOT NULL COMMENT '角色名称',
    `role_des` VARCHAR(100) NOT NULL COMMENT '角色描述',
    `created_time` DATETIME    COMMENT '创建时间' ,
    `created_by` VARCHAR(32)    COMMENT '创建时间',
    `updated_time` DATETIME    COMMENT '更新时间' ,
    `updated_by` VARCHAR(32)    COMMENT '更新人' ,
    `is_delete` BIT NOT NULL  DEFAULT 0 COMMENT '是否删除',
    PRIMARY KEY (role_id),
    INDEX (is_delete)
)COMMENT '用户角色表' ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1000;

INSERT INTO `movie_share`.`role` (`role_name`, `role_des`, `created_time`, `created_by`, `updated_time`, `updated_by`, `is_delete`) VALUES ('visitor', '游客', '2019-12-29 19:39:52', 'lidihao', '2019-12-29 19:40:07', 'lidihao', DEFAULT);

INSERT INTO `movie_share`.`role` (`role_name`, `role_des`, `created_time`, `created_by`, `updated_time`, `updated_by`, `is_delete`) VALUES ('admin', '管理员，复制系统的管理', '2019-12-29 19:35:55', 'lidihao', '2019-12-29 19:36:03', 'lidihao', DEFAULT);

INSERT INTO `movie_share`.`role` (`role_name`, `role_des`, `created_time`, `created_by`, `updated_time`, `updated_by`, `is_delete`) VALUES ('user', '普通用户', '2019-12-29 19:39:52', 'lidihao', '2019-12-29 19:40:07', 'lidihao', DEFAULT);

create table `user_role`(
    `user_role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_id` int(11) NOT NULL COMMENT 'user_id',
    `role_id` int(11) NOT NULL COMMENT 'role_id',
    `created_time` DATETIME    COMMENT '创建时间' ,
    `created_by` VARCHAR(32)    COMMENT '创建时间',
    `updated_time` DATETIME    COMMENT '更新时间' ,
    `updated_by` VARCHAR(32)    COMMENT '更新人' ,
    `is_delete` int(1) NOT NULL  DEFAULT 0 COMMENT '是否删除',
    PRIMARY KEY (user_role_id),
    INDEX (is_delete)
)COMMENT '用户-角色关联表' ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1000;

create table `menu`(
    `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `menu_name` VARCHAR(100) NOT NULL COMMENT '菜单名',
    `menu_eng` VARCHAR(100) NOT NULL COMMENT '菜单英文名',
    `menu_des` VARCHAR(200) NOT NULL COMMENT '菜单描述',
    `sort` int NOT NULL DEFAULT 0 NULL COMMENT '排序',
    `menu_url` VARCHAR(200) NOT NULL DEFAULT '' COMMENT '菜单url',
    `parent_menu_id` int(11) NOT NULL DEFAULT -1 COMMENT '父级菜单，顶级菜单的parent_menu_id',
    `has_child` BIT DEFAULT 0 COMMENT '是否有子菜单',
    `created_time` DATETIME    COMMENT '创建时间' ,
    `created_by` VARCHAR(32)    COMMENT '创建时间',
    `updated_time` DATETIME    COMMENT '更新时间' ,
    `updated_by` VARCHAR(32)    COMMENT '更新人' ,
    `is_delete` BIT NOT NULL  DEFAULT 0 COMMENT '是否删除',
    PRIMARY KEY (menu_id),
    INDEX (is_delete)
)COMMENT '菜单表' ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1000;

INSERT INTO `movie_share`.`menu` (`menu_name`, `menu_eng`, `menu_des`, `sort`, `menu_url`, `parent_menu_id`, `has_child`, `created_time`, `created_by`, `updated_time`, `updated_by`, `is_delete`) VALUES ('视频类型', 'video-type', '视频分类', 1, '', 0, true, '2019-12-29 19:45:41', 'lidihao', '2019-12-29 19:45:49', 'lidihao', DEFAULT);

INSERT INTO `movie_share`.`menu` (`menu_name`, `menu_eng`, `menu_des`, `sort`, `menu_url`, `parent_menu_id`, `has_child`, `created_time`, `created_by`, `updated_time`, `updated_by`, `is_delete`) VALUES ('动漫', 'video-type-animate', '动漫类型视频', 2, '/video/category-detail?type=animate', 1000, DEFAULT, '2019-12-29 19:47:24', 'lidihao', '2019-12-29 19:47:32', 'lidihao', DEFAULT);

CREATE TABLE `picture`  (
    `picture_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `file_name` varchar(255) NOT NULL COMMENT '图片名称',
    `height` int NOT NULL COMMENT '图片高度',
    `size` bigint NOT NULL COMMENT '图片大小',
    `url` varchar(255) NOT NULL COMMENT '图片地址',
    `width` int NOT NULL COMMENT '图片宽度',
    `created_time` DATETIME    COMMENT '创建时间' ,
    `created_by` VARCHAR(32)    COMMENT '创建时间',
    `updated_time` DATETIME    COMMENT '更新时间' ,
    `updated_by` VARCHAR(32)    COMMENT '更新人' ,
    `is_delete` int(1) NOT NULL  DEFAULT 0 COMMENT '是否删除',
    PRIMARY KEY (`picture_id`),
    INDEX (is_delete)
)COMMENT '图片' ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1000;

create table `role_menu`(
    `role_menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `role_id` int(11) NOT NULL COMMENT 'role_id',
    `menu_id` int(11) NOT NULL COMMENT 'menu_id',
    `created_time` DATETIME    COMMENT '创建时间' ,
    `created_by` VARCHAR(32)    COMMENT '创建时间',
    `updated_time` DATETIME    COMMENT '更新时间' ,
    `updated_by` VARCHAR(32)    COMMENT '更新人' ,
    `is_delete` int(1) NOT NULL  DEFAULT 0 COMMENT '是否删除',
    PRIMARY KEY (role_menu_id),
    INDEX (is_delete)
)COMMENT '角色-菜单表' ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1000;
INSERT INTO `movie_share`.`role_menu` (`role_id`, `menu_id`, `created_time`, `created_by`, `updated_time`, `updated_by`, `is_delete`) VALUES (1000, 1000, '2019-12-29 19:49:05', 'lidihao', '2019-12-29 19:49:10', 'lidihao', DEFAULT);
INSERT INTO `movie_share`.`role_menu` (`role_id`, `menu_id`, `created_time`, `created_by`, `updated_time`, `updated_by`, `is_delete`) VALUES (1000, 1001, '2019-12-29 19:49:06', 'lidihao', '2019-12-29 19:49:11', 'lidihao', DEFAULT);

create table `permission`(
    `permission_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `permission_name` VARCHAR(100) NOT NULL COMMENT '名字',
    `permission_des` VARCHAR(200) NOT NULL COMMENT '描述',
    `permission_action` VARCHAR(200) NOT NULL COMMENT '权限实体',
    `created_time` DATETIME    COMMENT '创建时间' ,
    `created_by` VARCHAR(32)    COMMENT '创建时间',
    `updated_time` DATETIME    COMMENT '更新时间' ,
    `updated_by` VARCHAR(32)    COMMENT '更新人' ,
    `is_delete` int(1) NOT NULL  DEFAULT 0 COMMENT '是否删除',
    PRIMARY KEY (permission_id),
    INDEX (is_delete)
)COMMENT '需要权限校验的url表' ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1000;

create table `role_permission`(
    `role_permission_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `role_id` int(11) NOT NULL COMMENT 'role_id',
    `permission_id` int(11) NOT NULL COMMENT 'permission_id',
    `created_time` DATETIME    COMMENT '创建时间' ,
    `created_by` VARCHAR(32)    COMMENT '创建时间',
    `updated_time` DATETIME    COMMENT '更新时间' ,
    `updated_by` VARCHAR(32)    COMMENT '更新人' ,
    `is_delete` int(1) NOT NULL  DEFAULT 0 COMMENT '是否删除',
    PRIMARY KEY (role_permission_id),
    INDEX (is_delete)
)COMMENT 'role-url表' ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1000;

create table `follow`(
    `follow_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_id` int(11) NOT NULL COMMENT '粉丝',
    `followed_user_id` int(11) NOT NULL COMMENT '被关注者',
    `created_time` DATETIME    COMMENT '创建时间' ,
    `created_by` VARCHAR(32)    COMMENT '创建时间',
    `updated_time` DATETIME    COMMENT '更新时间' ,
    `updated_by` VARCHAR(32)    COMMENT '更新人' ,
    `is_delete` int(1) NOT NULL  DEFAULT 0 COMMENT '是否删除',
    PRIMARY KEY (follow_id),
    INDEX (user_id),
    INDEX (follow_id),
    INDEX (is_delete)
)COMMENT '关注表' ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1000;

create table `category`(
    `category_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `category_name` VARCHAR(100) NOT NULL COMMENT '类别名',
    `category_des` VARCHAR(200) NOT NULL COMMENT '描述',
    `created_time` DATETIME    COMMENT '创建时间' ,
    `created_by` VARCHAR(32)    COMMENT '创建时间',
    `updated_time` DATETIME    COMMENT '更新时间' ,
    `updated_by` VARCHAR(32)    COMMENT '更新人' ,
    `is_delete` int(1) NOT NULL  DEFAULT 0 COMMENT '是否删除',
    PRIMARY KEY (category_id),
    INDEX (is_delete)
)COMMENT '视频类别表' ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1000;

create table `tag`(
    `tag_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `tag_name` VARCHAR(100) NOT NULL COMMENT '标签名称',
    `tag_desc` VARCHAR(200) NOT NULL COMMENT '标签描述',
    `created_time` DATETIME    COMMENT '创建时间' ,
    `created_by` VARCHAR(32)    COMMENT '创建时间',
    `updated_time` DATETIME    COMMENT '更新时间' ,
    `updated_by` VARCHAR(32)    COMMENT '更新人' ,
    `is_delete` int(1) NOT NULL  DEFAULT 0 COMMENT '是否删除',
    PRIMARY KEY (tag_id),
    INDEX (is_delete)
)COMMENT '视频标签表' ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1000;

create table `video`(
    `video_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `video_title` VARCHAR(100) NOT NULL COMMENT '视频题目',
    `video_poster_id` int(11) COMMENT '视频海报url',
    `video_play_count` BIGINT NOT NULL COMMENT '视频播放数',
    `video_comment_person` BIGINT NOT NULL COMMENT '视频评论人数',
    `video_desc` VARCHAR(400) NOT NULL COMMENT '视频简介',
    `video_rate` double NOT NULL DEFAULT 0.0 COMMENT '视频的评分,用于视频的推荐',
    `upload_user_id` int(11) NOT NULL COMMENT '视频上传user_id',
    `category_id` int(11) NOT NULL COMMENT '视频的类别',
    `created_time` DATETIME    COMMENT '创建时间' ,
    `created_by` VARCHAR(32)    COMMENT '创建时间',
    `updated_time` DATETIME    COMMENT '更新时间' ,
    `updated_by` VARCHAR(32)    COMMENT '更新人' ,
    `is_delete` int(1) NOT NULL  DEFAULT 0 COMMENT '是否删除',
    PRIMARY KEY (video_id),
    INDEX (is_delete)
)COMMENT '视频详情表' ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1000;

create table `episode`(
    `episode_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `episode_name` VARCHAR(100) NOT NULL COMMENT '剧集名称',
    `episode_url` VARCHAR(100) NOT NULL COMMENT '视频url',
    `poster_id` int(11) COMMENT '',
    `video_file_id` int(11) NOT NULL ,
    `episode_index` int NOT NULL COMMENT '剧集的索引',
    `video_id` int(11) NOT NULL COMMENT '属于哪一个视频的剧集',
    `created_time` DATETIME    COMMENT '创建时间' ,
    `created_by` VARCHAR(32)    COMMENT '创建时间',
    `updated_time` DATETIME    COMMENT '更新时间' ,
    `updated_by` VARCHAR(32)    COMMENT '更新人' ,
    `is_delete` int(1) NOT NULL  DEFAULT 0 COMMENT '是否删除',
    PRIMARY KEY (episode_id),
    INDEX (is_delete)
)COMMENT '视频剧集详情表' ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1000;

create table `video_tag`(
    `video_tag_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `video_id` int(11) NOT NULL COMMENT 'video_id',
    `tag_id` int(11) NOT NULL COMMENT 'tag_id',
    `category_id` int(11) NOT NULL COMMENT '视频的类别',
    `created_time` DATETIME    COMMENT '创建时间' ,
    `created_by` VARCHAR(32)    COMMENT '创建时间',
    `updated_time` DATETIME    COMMENT '更新时间' ,
    `updated_by` VARCHAR(32)    COMMENT '更新人' ,
    `is_delete` int(1) NOT NULL  DEFAULT 0 COMMENT '是否删除',
    PRIMARY KEY (video_tag_id),
    INDEX (is_delete)
)COMMENT '视频标签关系表' ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1000;


create table `video_comment`(
    `comment_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `comment_content` TEXT NOT NULL COMMENT '评论内容',
    `comment_up` int NOT NULL DEFAULT 0 COMMENT '点赞人数',
    `comment_down` int NOT NULL DEFAULT 0 COMMENT '踩的人数',
    `comment_user_id` int(11) NOT NULL COMMENT '评论人',
    `rate` DOUBLE NOT NULL DEFAULT 0.0 COMMENT '评分',
    `comment_type` int NOT NULL COMMENT '评论类型',
    `root_comment_id` int(11) NOT NULL COMMENT '顶级评论',
    `reply_comment_id` int(11) NOT NULL COMMENT '回复评论的id',
    `ref_id` int(11) NOT NULL COMMENT '视频id',
    `created_time` DATETIME    COMMENT '创建时间' ,
    `created_by` VARCHAR(32)    COMMENT '创建时间',
    `updated_time` DATETIME    COMMENT '更新时间' ,
    `updated_by` VARCHAR(32)    COMMENT '更新人' ,
    `is_delete` int(1) NOT NULL  DEFAULT 0 COMMENT '是否删除',
    PRIMARY KEY (comment_id),
    INDEX (is_delete)
)COMMENT '视频评论' ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1000;


create table `report_reason`(
    `reason_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `reason_name` VARCHAR(100) NOT NULL COMMENT '原因名称',
    `reason_desc` VARCHAR(200) NOT NULL COMMENT '原因描述',
    `created_time` DATETIME    COMMENT '创建时间' ,
    `created_by` VARCHAR(32)    COMMENT '创建时间',
    `updated_time` DATETIME    COMMENT '更新时间' ,
    `updated_by` VARCHAR(32)    COMMENT '更新人' ,
    `is_delete` int(1) NOT NULL  DEFAULT 0 COMMENT '是否删除',
    PRIMARY KEY (reason_id),
    INDEX (is_delete)
)COMMENT '举报原因' ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1000;

create table `report_comment`(
    `report_comment_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `report_reason_id` int(11) NOT NULL ,
    `comment_id` int(11) NOT NULL ,
    `created_time` DATETIME    COMMENT '创建时间' ,
    `created_by` VARCHAR(32)    COMMENT '创建时间',
    `updated_time` DATETIME    COMMENT '更新时间' ,
    `updated_by` VARCHAR(32)    COMMENT '更新人' ,
    `is_delete` int(1) NOT NULL  DEFAULT 0 COMMENT '是否删除',
    PRIMARY KEY (report_comment_id),
    INDEX (is_delete)
)COMMENT '举报的评论' ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1000;

create table `favorite_video`(
    `favorite_video_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `video_id` int(11) NOT NULL COMMENT '视频id',
    `user_id` int(11) NOT NULL COMMENT '收藏人',
    `created_time` DATETIME    COMMENT '创建时间' ,
    `created_by` VARCHAR(32)    COMMENT '创建时间',
    `updated_time` DATETIME    COMMENT '更新时间' ,
    `updated_by` VARCHAR(32)    COMMENT '更新人' ,
    `is_delete` int(1) NOT NULL  DEFAULT 0 COMMENT '是否删除',
    PRIMARY KEY (favorite_video_id),
    INDEX (is_delete)
)COMMENT '收藏视频' ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1000;

create table `history_video`(
    `history_video_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `video_id` int(11) NOT NULL COMMENT '视频id',
    `user_id` int(11) NOT NULL COMMENT '收藏人',
    `created_time` DATETIME    COMMENT '创建时间' ,
    `created_by` VARCHAR(32)    COMMENT '创建时间',
    `updated_time` DATETIME    COMMENT '更新时间' ,
    `updated_by` VARCHAR(32)    COMMENT '更新人' ,
    `is_delete` int(1) NOT NULL  DEFAULT 0 COMMENT '是否删除',
    PRIMARY KEY (history_video_id),
    INDEX (is_delete)
)COMMENT '历史观看' ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1000;

create table `person_space_right`(
    `person_space_right_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `can_see_history` BIT NOT NULL DEFAULT 1 COMMENT '可以查看历史',
    `can_see_fans` BIT NOT NULL DEFAULT 1 COMMENT '可以查看粉丝',
    `can_see_favorite` BIT NOT NULL DEFAULT 1 COMMENT '可以查看收藏',
    `can_see_subscribe` BIT NOT NULL DEFAULT 1 COMMENT '可以查看关注人',
    `created_time` DATETIME    COMMENT '创建时间' ,
    `created_by` VARCHAR(32)    COMMENT '创建时间',
    `updated_time` DATETIME    COMMENT '更新时间' ,
    `updated_by` VARCHAR(32)    COMMENT '更新人' ,
    `is_delete` int(1) NOT NULL  DEFAULT 0 COMMENT '是否删除',
    PRIMARY KEY (person_space_right_id),
    INDEX (is_delete)
)COMMENT '个人空间权限表' ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1000;

create table `reply_notify`(
    `reply_notify_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `comment_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论id',
    `user_id` int(11) NOT NULL COMMENT '回复user_id',
    `notify_user_id` int(11) NOT NULL COMMENT '提醒user_id',
    `has_read` BIT NOT NULL DEFAULT 0 COMMENT '是否消息已读',
    `created_time` DATETIME    COMMENT '创建时间' ,
    `created_by` VARCHAR(32)    COMMENT '创建时间',
    `updated_time` DATETIME    COMMENT '更新时间' ,
    `updated_by` VARCHAR(32)    COMMENT '更新人' ,
    `is_delete` int(1) NOT NULL  DEFAULT 0 COMMENT '是否删除',
    PRIMARY KEY (reply_notify_id),
    INDEX (is_delete)
)COMMENT '回复评论提醒' ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1000;

create table `system_message`(
    `system_message_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `system_message_content` TEXT NOT NULL COMMENT '消息内功',
    `user_id` int(11) NOT NULL COMMENT 'user_id',
    `has_read` BIT NOT NULL DEFAULT 0 COMMENT '是否已读',
    `created_time` DATETIME    COMMENT '创建时间' ,
    `created_by` VARCHAR(32)    COMMENT '创建时间',
    `updated_time` DATETIME    COMMENT '更新时间' ,
    `updated_by` VARCHAR(32)    COMMENT '更新人' ,
    `is_delete` int(1) NOT NULL  DEFAULT 0 COMMENT '是否删除',
    PRIMARY KEY (system_message_id),
    INDEX (is_delete)
)COMMENT '系统消息提醒' ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1000;

create table `im_message`(
    `im_message_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `send_user_id` int NOT NULL COMMENT '发送人',
    `receive_user_id` int NOT NULL COMMENT '接受人',
    `has_read` BIT NOT NULL DEFAULT 0 COMMENT '是否已读',
    `message_content` TEXT NOT NULL COMMENT '评论内容',
    `created_time` DATETIME    COMMENT '创建时间' ,
    `created_by` VARCHAR(32)    COMMENT '创建时间',
    `updated_time` DATETIME    COMMENT '更新时间' ,
    `updated_by` VARCHAR(32)    COMMENT '更新人' ,
    `is_delete` int(1) NOT NULL  DEFAULT 0 COMMENT '是否删除',
    PRIMARY KEY (im_message_id),
    INDEX (is_delete)
)COMMENT '私信' ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1000;

create table `message_right`(
    `message_right_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_id` int(11) NOT NULL COMMENT '权限设置人',
    `receive_reply_notify` BIT NOT NULL DEFAULT 1 COMMENT '是否接受回复',
    `receive_system_notify` BIT NOT NULL DEFAULT 1 COMMENT '是否接受系统提醒',
    `receive_im_message` BIT NOT NULL DEFAULT 1 COMMENT '是否接受私信',
    `created_time` DATETIME    COMMENT '创建时间' ,
    `created_by` VARCHAR(32)    COMMENT '创建时间',
    `updated_time` DATETIME    COMMENT '更新时间' ,
    `updated_by` VARCHAR(32)    COMMENT '更新人' ,
    `is_delete` int(1) NOT NULL  DEFAULT 0 COMMENT '是否删除',
    PRIMARY KEY (message_right_id),
    INDEX (is_delete)
)COMMENT '消息设置' ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1000;

create table `video_approval`(
    `video_approval_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `upload_user_id` int(11) NOT NULL COMMENT '上传人id',
    `poster_id` int(11) NOT NULL COMMENT '视频posterURL',
    `title` VARCHAR(100) NOT NULL COMMENT '视频题目审批',
    `introduce` VARCHAR(200) NOT NULL COMMENT '视频简介',
    `category_id` int(11) NOT NULL COMMENT '视频类别',
    `video_id` int(11) NOT NULL DEFAULT -1 COMMENT '对应的视频id',
    `approval_type` int NOT NULL DEFAULT 0 COMMENT '审批状态',
    `created_time` DATETIME    COMMENT '创建时间' ,
    `created_by` VARCHAR(32)    COMMENT '创建时间',
    `updated_time` DATETIME    COMMENT '更新时间' ,
    `updated_by` VARCHAR(32)    COMMENT '更新人' ,
    `is_delete` int(1) NOT NULL  DEFAULT 0 COMMENT '是否删除',
    PRIMARY KEY (video_approval_id),
    INDEX (is_delete)
)COMMENT '视频审批' ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1000;

create table `tag_video_approval`(
    `tag_video_approval` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `tag_id` int(11) NOT NULL COMMENT '标签id',
    `video_approval_id` int(11) NOT NULL COMMENT '视频审批id',
    primary key (tag_video_approval)
)COMMENT '标签与视频申请' ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1000;

create table `video_file`(
    `video_file_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `uploader_id` int(11) NOT NULL COMMENT '主键',
    `sort` int not null DEFAULT 0 COMMENT '排序字段',
    `video_apply_id` int(11) DEFAULT -1 COMMENT'审批Id',
    `size` bigint NOT NULL COMMENT '大小',
    `file_name` VARCHAR(100) NOT NULL COMMENT '名字',
    `approval_type` int NOT NULL DEFAULT 0 COMMENT '审批状态',
    `file_url` VARCHAR(100) NOT NULL COMMENT '视频url',
    `file_type` varchar(50) NOT NULL COMMENT '视频类型',
    `poster_id` int(11) COMMENT '',
    `created_time` DATETIME    COMMENT '创建时间' ,
    `created_by` VARCHAR(32)    COMMENT '创建时间',
    `updated_time` DATETIME    COMMENT '更新时间' ,
    `updated_by` VARCHAR(32)    COMMENT '更新人' ,
    `is_delete` int(1) NOT NULL  DEFAULT 0 COMMENT '是否删除',
    PRIMARY KEY (video_file_id),
    INDEX (is_delete)
)COMMENT '上传的文件' ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1000;