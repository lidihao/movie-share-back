package com.hao.movieshareback.controller;

import cn.hutool.db.sql.Order;
import com.hao.movieshareback.annotation.auth.AnonymousAccess;
import com.hao.movieshareback.annotation.log.Log;
import com.hao.movieshareback.model.Video;
import com.hao.movieshareback.service.UserService;
import com.hao.movieshareback.service.VideoService;
import com.hao.movieshareback.vo.ResultBody;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;

@RequestMapping("/video")
@RestController
public class VideoController {

    @Autowired
    private VideoService videoService;

    @Log(value = "play_video",businessType = "play_video")
    @AnonymousAccess
    @GetMapping("/videoDetail")
    public ResultBody getVideoDetail(Integer videoId){
        return ResultBody.success(videoService.getVideoDetail(videoId));
    }

    @AnonymousAccess
    @GetMapping("/getUploadVideoByCondition")
    public ResultBody getUploadVideoByCondition(Video condition,Integer pageNum,Integer pageSize){
        return ResultBody.success(videoService.getVideoDetailByCondition(condition,pageNum,pageSize));
    }

    @AnonymousAccess
    @PostMapping("/incrementPlayCount")
    public ResultBody incrementPlayCount(Integer videoId){
        videoService.incrementVideoPlayCount(videoId);
        return ResultBody.success();
    }

    @AnonymousAccess
    @GetMapping("/getVideoByCategory")
    public ResultBody getVideoByCategory(String orderField,String categoryName,String tagName,Integer pageNum,Integer pageSize){
        try {
            return ResultBody.success(videoService.getVideoByCategory(orderField,categoryName,tagName,pageNum,pageSize));
        } catch (IOException e) {
            e.printStackTrace();
            return ResultBody.error("");
        }
    }

    @Log(value = "search_video",businessType = "search_video")
    @AnonymousAccess
    @GetMapping("/searchVideo")
    public ResultBody getVideoBySearchKey(String orderField, String searchKey, String tagName,
                                          Integer categoryId, String startDate,String endDate,Integer pageNum, Integer pageSize){
        if (!Strings.isNotBlank(searchKey)){
            return ResultBody.success();
        }
        if (pageNum==null){
            pageNum=1;
        }
        if (pageSize==null){
            pageSize=12;
        }
        try {
            return ResultBody.success(videoService.searchVideo(orderField, searchKey, tagName, categoryId, startDate, endDate, pageNum, pageSize));
        } catch (IOException e) {
            e.printStackTrace();
            return ResultBody.error("error");
        }
    }

    @Log(value = "end_play_video",businessType = "end_play_video")
    @AnonymousAccess
    @PostMapping("/endplayVideo")
    public ResultBody endPlayVideo(Integer videoId){
        return ResultBody.success();
    }
}
