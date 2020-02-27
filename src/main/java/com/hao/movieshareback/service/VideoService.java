package com.hao.movieshareback.service;

import com.hao.movieshareback.dao.*;
import com.hao.movieshareback.model.*;
import com.hao.movieshareback.vo.VideoDetailVo;
import com.hao.movieshareback.vo.auth.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {
    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private PictureMapper pictureMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private TagService tagService;

    public VideoDetailVo getVideoDetail(Integer videoId){
        Video video = videoMapper.getVideo(videoId);
        if (video==null){
            return null;
        }
        UserVo userVo = userService.getUserVoByUserId(video.getUploadUserId());
        Picture picture= pictureMapper.selectPictureById(video.getVideoPosterId());
        Category category = categoryMapper.selectCategoryById(video.getCategoryId());
        List<Tag> tagList = tagService.getTagListByVideoId(video.getVideoId());
        return new VideoDetailVo(video.getVideoTitle(),category,video.getCreatedTime(),video.getVideoDesc(),tagList,userVo,video.getVideoPlayCount(),video.getVideoCommentPerson());
    }
}
