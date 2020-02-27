package com.hao.movieshareback.service;

import com.hao.movieshareback.dao.EpisodeMapper;
import com.hao.movieshareback.dao.PictureMapper;
import com.hao.movieshareback.model.Episode;
import com.hao.movieshareback.model.Picture;
import com.hao.movieshareback.vo.EpisodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EpisodeService {

    @Autowired
    private EpisodeMapper episodeMapper;
    @Autowired
    private PictureMapper pictureMapper;

    public List<EpisodeVo> listEpisodeByVideoId(Integer videoId){
        List<Episode> episodeList = episodeMapper.getEpisodeByVideoId(videoId);
        return episodeList.stream().map((episode ->{
            Picture picture=pictureMapper.selectPictureById(episode.getPosterId());
            return new EpisodeVo(episode.getEpisodeId(),
                episode.getEpisodeName(),episode.getVideoFileId(),episode.getEpisodeUrl(),
                    picture.getUrl(),episode.getEpisodeIndex(),episode.getVideoId()
            );
        })).collect(Collectors.toList());
    }

    public EpisodeVo getEpisodeByEpisodeId(Integer episodeId){
        Episode episode = episodeMapper.getEpisodeByEpisodeId(episodeId);
        Picture picture =pictureMapper.selectPictureById(episode.getPosterId());
        return new EpisodeVo(episode.getEpisodeId(),
                episode.getEpisodeName(),episode.getVideoFileId(),episode.getEpisodeUrl(),
                picture.getUrl(),episode.getEpisodeIndex(),episode.getVideoId()
        );
    }
}
