package com.hao.movieshareback.dao;

import com.hao.movieshareback.model.Follow;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FollowMapper {
    void save(Follow follow);
    Integer isFollow(Follow follow);
    void unFollow(Follow follow);
    Integer getFollowingCount(Integer userId);
    Integer getFollowedCount(Integer userId);
}
