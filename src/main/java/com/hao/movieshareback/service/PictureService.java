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
}
