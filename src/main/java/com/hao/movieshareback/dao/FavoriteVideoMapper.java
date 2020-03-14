package com.hao.movieshareback.dao;

import com.hao.movieshareback.model.FavoriteVideo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FavoriteVideoMapper {
    void save(FavoriteVideo favoriteVideo);
    void unFavorite(FavoriteVideo favoriteVideo);
    Integer isFavorite(FavoriteVideo favoriteVideo);
    Integer getFavoriteCount(Integer videoId);
}
