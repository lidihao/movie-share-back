package com.hao.movieshareback.dao;

import com.hao.movieshareback.model.VideoFile;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface VideoFileMapper {
    int save(VideoFile videoFile);
}
