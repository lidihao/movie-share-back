package com.hao.movieshareback.service;

import com.hao.movieshareback.dao.PictureMapper;
import com.hao.movieshareback.model.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureService {
    @Autowired
    private PictureMapper pictureMapper;


    public Picture getPicById(Integer picId){
        return pictureMapper.selectPictureById(picId);
    }

    public String getUserAvatar(Integer picId){
        Picture picture = pictureMapper.selectPictureById(picId);
        if (picture==null){
            return getDefaultAvatarUrl();
        }else {
            return picture.getUrl();
        }
    }

    public String getUserSkinUrl(Integer picId){
        Picture picture = pictureMapper.selectPictureById(picId);
        if (picture==null){
            return getDefaultSkin();
        }else {
            return picture.getUrl();
        }
    }

    private String getDefaultAvatarUrl(){
        return "/image/default-avatar.jpeg";
    }

    private String getDefaultSkin(){
        return "/image/default-skin.jpeg";
    }
}
