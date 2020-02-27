package com.hao.movieshareback.controller;

import com.hao.movieshareback.annotation.auth.AnonymousAccess;
import com.hao.movieshareback.model.Episode;
import com.hao.movieshareback.service.EpisodeService;
import com.hao.movieshareback.vo.ResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/episode")
@RestController
public class EpisodeController {

    @Autowired
    private EpisodeService episodeService;


    @AnonymousAccess
    @GetMapping("/listEpisode")
    public ResultBody listEpisode(Integer videoId){
        return ResultBody.success(episodeService.listEpisodeByVideoId(videoId));
    }

    @AnonymousAccess
    @GetMapping("/episodeDetail")
    public ResultBody getEpisodeDetail(Integer episodeId){
        return ResultBody.success(episodeService.getEpisodeByEpisodeId(episodeId));
    }
}
