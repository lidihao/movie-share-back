package com.hao.movieshareback.dao;

import com.hao.movieshareback.model.Picture;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface PictureMapper {
    Picture selectPictureById(Integer pictureId);
    int save(Picture picture);
}
