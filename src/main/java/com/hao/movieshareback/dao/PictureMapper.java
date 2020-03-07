package com.hao.movieshareback.dao;

import com.hao.movieshareback.model.Picture;
import com.hao.movieshareback.model.User;
import com.hao.movieshareback.model.UserSkin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PictureMapper {
    Picture selectPictureById(Integer pictureId);
    int save(Picture picture);
    List<UserSkin> selectSkinListById(Integer userId);
    void saveUserSkin(UserSkin userSkin);
    void deletePicById(Integer pictureId);
    void deleteUserSkin(UserSkin userSkin);
}
