package com.hao.movieshareback.controller;

import com.hao.movieshareback.annotation.auth.AnonymousAccess;
import com.hao.movieshareback.model.Follow;
import com.hao.movieshareback.model.User;
import com.hao.movieshareback.service.FollowService;
import com.hao.movieshareback.vo.ResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/follow")
public class FollowController {

    @Autowired
    private FollowService followService;

    @PostMapping("/followUser")
    public ResultBody followUser(@RequestBody Follow follow){
        followService.followUser(follow);
        return ResultBody.success();
    }

    @PostMapping("/isFollow")
    public ResultBody isFollow(@RequestBody Follow follow){
        return ResultBody.success(followService.isFollow(follow));
    }

    @PostMapping("/unFollow")
    public ResultBody unFollowUSer(@RequestBody Follow follow){
        followService.unFollowUser(follow);
        return ResultBody.success();
    }

    @AnonymousAccess
    @GetMapping("/getFollowingCount")
    public ResultBody getFollowingCount(Integer userId){
        return ResultBody.success(followService.getFollowingCount(userId));
    }

    @AnonymousAccess
    @GetMapping("/getFollowedCount")
    public ResultBody getFollowedCount(Integer userId){
        return ResultBody.success(followService.getFollowedCount(userId));
    }

    @AnonymousAccess
    @GetMapping("/getFollowingUserList")
    public ResultBody getFollowingUserList(User condition, Integer pageNum, Integer pageSize){
        return ResultBody.success(followService.getFollowingUserList(condition,pageNum,pageSize));
    }

    @AnonymousAccess
    @GetMapping("/getFollowedUserList")
    public ResultBody getFollowedUserList(User condition,Integer pageNum,Integer pageSize){
        return ResultBody.success(followService.getFollowedUserList(condition, pageNum, pageSize));
    }
}
