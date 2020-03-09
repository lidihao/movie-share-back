package com.hao.movieshareback.dao;

import com.hao.movieshareback.model.User;
import com.hao.movieshareback.model.UserSkin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    /**
     * 新增用户
     * @param user
     * @return
     */
    int save (User user);

    User getUserByUserName(String userName);

    int getCountByEmail(String email);

    int getCountByUserName(String userName);

    int activeUser(String userName);

    User getUserByUserId(Integer userId);

    void updateSkin(UserSkin userSkin);

    void updateUserAvatarUrl(Integer userId,Integer pictureId);
}
