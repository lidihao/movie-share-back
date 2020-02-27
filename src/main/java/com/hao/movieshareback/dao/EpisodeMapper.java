package com.hao.movieshareback.dao;

import com.hao.movieshareback.model.Episode;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EpisodeMapper {
    int save(Episode episode);
    void deleteEpisodeByVideoFileId(Integer videoFileId);
    List<Episode> getEpisodeByVideoId(Integer videoId);
    Episode getEpisodeByEpisodeId(Integer episodeId);
}
