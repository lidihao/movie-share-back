package com.hao.movieshareback.model;

/**
 * create table `episode`(
 *     `episode_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
 *     `episode_name` VARCHAR(100) NOT NULL COMMENT '剧集名称',
 *     `episode_url` VARCHAR(100) NOT NULL COMMENT '视频url',
 *     `poster_id` int(11) COMMENT '',
 *     `episode_index` int NOT NULL COMMENT '剧集的索引',
 *     `video_id` int(11) NOT NULL COMMENT '属于哪一个视频的剧集',
 *     `episode_time` int NOT NULL COMMENT '剧集的时间',
 *     `created_time` DATETIME    COMMENT '创建时间' ,
 *     `created_by` VARCHAR(32)    COMMENT '创建时间',
 *     `updated_time` DATETIME    COMMENT '更新时间' ,
 *     `updated_by` VARCHAR(32)    COMMENT '更新人' ,
 *     `is_delete` int(1) NOT NULL  DEFAULT 0 COMMENT '是否删除',
 *     PRIMARY KEY (episode_id),
 *     INDEX (is_delete)
 * )COMMENT '视频剧集详情表' ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1000;
 */
public class Episode extends BaseModel {
    private Integer episodeId;
    private String episodeName;
    private Integer videoFileId;
    private String episodeUrl;
    private Integer posterId;
    private Integer episodeIndex;
    private Integer videoId;

    public Episode() {
    }

    public Episode(String episodeName,
                   String episodeUrl, Integer posterId,
                   Integer episodeIndex, Integer videoId,Integer videoFileId) {
        this.episodeName = episodeName;
        this.episodeUrl = episodeUrl;
        this.posterId = posterId;
        this.episodeIndex = episodeIndex;
        this.videoId = videoId;
        this.videoFileId = videoFileId;
    }

    public Integer getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(Integer episodeId) {
        this.episodeId = episodeId;
    }

    public String getEpisodeName() {
        return episodeName;
    }

    public void setEpisodeName(String episodeName) {
        this.episodeName = episodeName;
    }

    public String getEpisodeUrl() {
        return episodeUrl;
    }

    public void setEpisodeUrl(String episodeUrl) {
        this.episodeUrl = episodeUrl;
    }

    public Integer getPosterId() {
        return posterId;
    }

    public void setPosterId(Integer posterId) {
        this.posterId = posterId;
    }

    public Integer getEpisodeIndex() {
        return episodeIndex;
    }

    public void setEpisodeIndex(Integer episodeIndex) {
        this.episodeIndex = episodeIndex;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public Integer getVideoFileId() {
        return videoFileId;
    }

    public void setVideoFileId(Integer videoFileId) {
        this.videoFileId = videoFileId;
    }
}
