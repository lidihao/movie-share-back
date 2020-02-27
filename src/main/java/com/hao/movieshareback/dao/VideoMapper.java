package com.hao.movieshareback.dao;

import com.hao.movieshareback.model.Video;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface VideoMapper {
    int save(Video video);
    Video getVideo(Integer videoId);
}
