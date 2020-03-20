package com.hao.movieshareback.controller;

import com.hao.movieshareback.annotation.log.Log;
import com.hao.movieshareback.model.FavoriteVideo;
import com.hao.movieshareback.model.Video;
import com.hao.movieshareback.service.FavoriteVideoService;
import com.hao.movieshareback.service.VideoService;
import com.hao.movieshareback.vo.ResultBody;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/favorite")
@RestController
public class FavoriteVideoController {

    @Autowired
    private FavoriteVideoService favoriteVideoService;

    @Autowired
    private VideoService videoService;

    @Log(value = "favorite_video",businessType = "favorite_video")
    @PostMapping("/favoriteVideo")
    public ResultBody favoriteVideo(@RequestBody FavoriteVideo favoriteVideo){
        favoriteVideoService.favoriteVideo(favoriteVideo);
        return ResultBody.success();
    }

    @PostMapping("/unFavoriteVideo")
    public ResultBody unFavoriteVideo(FavoriteVideo favoriteVideo){
        favoriteVideoService.unFavoriteVideo(favoriteVideo);
        return ResultBody.success();
    }

    @GetMapping("/isFavorite")
    public ResultBody isFavorite(FavoriteVideo favoriteVideo){
        return ResultBody.success(favoriteVideoService.isFavorite(favoriteVideo));
    }

    @GetMapping("/getFavoriteCount")
    public ResultBody getFavoriteCount(Integer videoId){
        return ResultBody.success(favoriteVideoService.getFavoriteCount(videoId));
    }

    @GetMapping("/getFavoriteVideoList")
    public ResultBody getFavoriteVideoList(Video video, Integer userId, Integer pageNum, Integer pageSize){
        return ResultBody.success(videoService.getFavoriteVideo(video,userId,pageNum,pageSize));
    }
}
