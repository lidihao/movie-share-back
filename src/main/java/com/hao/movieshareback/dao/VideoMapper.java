package com.hao.movieshareback.dao;

import com.hao.movieshareback.model.Video;
import com.hao.movieshareback.vo.Page;
import com.hao.movieshareback.vo.PageList;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface VideoMapper {
    int save(Video video);
    Video getVideo(Integer videoId);
    PageList<Video> getVideoDetailListLikeName(Page page,Video video);
    void updateRate(Double rate,Integer videoId);
    void incrementVideoPlayCount(Integer videoId);
    PageList<Video> getFavoriteVideoDetailList(Page page,Video video,Integer userId);
}
