package com.hao.movieshareback.service;

import com.hao.movieshareback.dao.FavoriteVideoMapper;
import com.hao.movieshareback.model.FavoriteVideo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteVideoService {

    @Autowired
    private FavoriteVideoMapper favoriteVideoMapper;

    public void favoriteVideo(FavoriteVideo favoriteVideo){
        if (!isFavorite(favoriteVideo)){
            favoriteVideoMapper.save(favoriteVideo);
        }
    }

    public void unFavoriteVideo(FavoriteVideo favoriteVideo){
        favoriteVideoMapper.unFavorite(favoriteVideo);
    }

    public boolean isFavorite(FavoriteVideo favoriteVideo){
        return favoriteVideoMapper.isFavorite(favoriteVideo)!=0;
    }

    public Integer getFavoriteCount(Integer videoId){
        return favoriteVideoMapper.getFavoriteCount(videoId);
    }
}
