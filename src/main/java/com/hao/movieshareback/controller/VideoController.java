package com.hao.movieshareback.controller;

import com.hao.movieshareback.annotation.auth.AnonymousAccess;
import com.hao.movieshareback.service.UserService;
import com.hao.movieshareback.service.VideoService;
import com.hao.movieshareback.vo.ResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/video")
@RestController
public class VideoController {

    @Autowired
    private VideoService videoService;

    @AnonymousAccess
    @GetMapping("/videoDetail")
    public ResultBody getVideoDetail(Integer videoId){
        return ResultBody.success(videoService.getVideoDetail(videoId));
    }
}
