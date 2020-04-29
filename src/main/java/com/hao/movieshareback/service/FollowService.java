package com.hao.movieshareback.service;

import com.hao.movieshareback.dao.FollowMapper;
import com.hao.movieshareback.dao.PictureMapper;
import com.hao.movieshareback.dao.UserMapper;
import com.hao.movieshareback.model.BaseModel;
import com.hao.movieshareback.model.Follow;
import com.hao.movieshareback.model.Picture;
import com.hao.movieshareback.model.User;
import com.hao.movieshareback.utils.SecurityUtils;
import com.hao.movieshareback.vo.Page;
import com.hao.movieshareback.vo.PageList;
import com.hao.movieshareback.vo.XPage;
import com.hao.movieshareback.vo.auth.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class FollowService {

    @Autowired
    private FollowMapper followMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PictureService pictureService;

    public void followUser(Follow follow){
        String userName= SecurityUtils.getUsername();
        if (!isFollow(follow)) {
            BaseModel.setNewCreate(follow,userName,new Date());
            BaseModel.setUpdated(follow,userName,new Date());
            followMapper.save(follow);
        }
    }

    public boolean isFollow(Follow follow){
        return !followMapper.isFollow(follow).equals(0);
    }

    public void unFollowUser(Follow follow){
        followMapper.unFollow(follow);
    }
    public Integer getFollowedCount(Integer userId){
        return followMapper.getFollowedCount(userId);
    }
    public Integer getFollowingCount(Integer userId){
        return followMapper.getFollowingCount(userId);
    }

    public XPage<UserVo> getFollowingUserList(User condition, Integer pageNum, Integer pageSize){
        Page page = new Page(pageNum,pageSize);
        PageList<User> userPageList = userMapper.getFollowingUserList(page,condition);
        PageList<UserVo> userVoPageList = new PageList<>();
        userVoPageList.setPageInfo(userPageList.getPageInfo());
        userPageList.forEach(user -> {
            String avatarUrl = pictureService.getUserAvatar(user.getAvatarPicId());
            userVoPageList.add(new UserVo(user.getUserId(),user.getUserName(),avatarUrl,user.getEmail(),null));
        });
        return XPage.wrap(userVoPageList);
    }

    public XPage<UserVo> getFollowedUserList(User condition,Integer pageNum,Integer pageSize){
        Page page = new Page(pageNum,pageSize);
        PageList<User> userPageList = userMapper.getFollowedUserList(page,condition);
        PageList<UserVo> userVoPageList = new PageList<>();
        userVoPageList.setPageInfo(userPageList.getPageInfo());
        userPageList.forEach(user -> {
            String avatarUrl = pictureService.getUserAvatar(user.getAvatarPicId());
            userVoPageList.add(new UserVo(user.getUserId(),user.getUserName(),avatarUrl,user.getEmail(),null));
        });
        return XPage.wrap(userVoPageList);
    }
}
