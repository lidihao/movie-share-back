package com.hao.movieshareback.controller;

import com.hao.movieshareback.annotation.auth.AnonymousAccess;
import com.hao.movieshareback.service.RecommendService;
import com.hao.movieshareback.vo.ResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/recommend")
@RestController
public class RecommendController {

    @Autowired
    private RecommendService recommendService;


    @AnonymousAccess
    @RequestMapping("/recentlyHot")
    public ResultBody getRecentlyHotVideo(Integer pageNum,Integer pageSize){
        return ResultBody.success(recommendService.getRecentlyHotVideo(pageNum,pageSize));
    }

    @AnonymousAccess
    @RequestMapping("/getCategoryRecentlyHot")
    public ResultBody getCategroyRecnetlyHot(Integer pageNum,Integer pageSize){
        return ResultBody.success(recommendService.getRecentlyHotVideoCategory(pageNum,pageSize));
    }

    @GetMapping("/getPesonalRecommend")
    public ResultBody getPersonalRecommend(Integer pageNum,Integer pageSize){
        return ResultBody.success(recommendService.getPersonalRecommendVideoList(pageNum,pageSize));
    }
}
