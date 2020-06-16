package com.hao.movieshareback.dao;

import com.hao.movieshareback.model.User;
import com.hao.movieshareback.model.UserSkin;
import com.hao.movieshareback.vo.Page;
import com.hao.movieshareback.vo.PageList;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


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

    User getUserByEmail(String email);

    void resetUserPassword(String password,String salt,Integer userId);

    PageList<User> getFollowingUserList(Page page,User user);

    PageList<User> getFollowedUserList(Page page,User user);

    List<User> getAllFollowedUserList(Integer userId);

    PageList<User> searchUser(Page page,String userName);

    void updateEmail(Integer userId,String email);

    PageList<User> searchUserForManager(Page page,String userName);
}
