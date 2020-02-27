package com.hao.movieshareback.vo;

public class EpisodeVo {
    private Integer episodeId;
    private String episodeName;
    private Integer videoFileId;
    private String episodeUrl;
    private String posterUrl;
    private Integer episodeIndex;
    private Integer videoId;

    public EpisodeVo(Integer episodeId, String episodeName, Integer videoFileId, String episodeUrl, String posterUrl, Integer episodeIndex, Integer videoId) {
        this.episodeId = episodeId;
        this.episodeName = episodeName;
        this.videoFileId = videoFileId;
        this.episodeUrl = episodeUrl;
        this.posterUrl = posterUrl;
        this.episodeIndex = episodeIndex;
        this.videoId = videoId;
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

    public Integer getVideoFileId() {
        return videoFileId;
    }

    public void setVideoFileId(Integer videoFileId) {
        this.videoFileId = videoFileId;
    }

    public String getEpisodeUrl() {
        return episodeUrl;
    }

    public void setEpisodeUrl(String episodeUrl) {
        this.episodeUrl = episodeUrl;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
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
}
