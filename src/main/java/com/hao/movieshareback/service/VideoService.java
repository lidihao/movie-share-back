package com.hao.movieshareback.service;

import com.hao.movieshareback.dao.*;
import com.hao.movieshareback.model.*;
import com.hao.movieshareback.vo.Page;
import com.hao.movieshareback.vo.PageList;
import com.hao.movieshareback.vo.VideoDetailVo;
import com.hao.movieshareback.vo.XPage;
import com.hao.movieshareback.vo.auth.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
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
        return toVideoDetailVo(video);
    }

    public XPage<VideoDetailVo> getVideoDetailByCondition(Video condition,Integer pageNum,Integer pageSize){
        Page page = new Page(pageNum,pageSize);
        page.setOrderColumn(Collections.singletonList("created_time"));
        page.setOrderType("DESC");
        PageList<Video> videos = videoMapper.getVideoDetailListLikeName(page,condition);
        PageList<VideoDetailVo> videoDetailVos = new PageList<>();
        videoDetailVos.setPageInfo(videos.getPageInfo());
        videos.forEach(video ->{
                videoDetailVos.add(toVideoDetailVo(video));
        });
        return XPage.wrap(videoDetailVos);
    }

    public void incrementVideoPlayCount(Integer videoId){
        videoMapper.incrementVideoPlayCount(videoId);
    }

    public XPage<VideoDetailVo> getFavoriteVideo(Video condition,Integer userId,Integer pageNum,Integer pageSize){
        Page page = new Page(pageNum,pageSize);
        PageList<Video> videos = videoMapper.getFavoriteVideoDetailList(page,condition,userId);
        PageList<VideoDetailVo> videoDetailVos = new PageList<>();
        videoDetailVos.setPageInfo(videos.getPageInfo());
        videos.forEach(video ->{
            videoDetailVos.add(toVideoDetailVo(video));
        });
        return XPage.wrap(videoDetailVos);
    }

    public XPage<VideoDetailVo> getVideoByCategory(String orderField,String categoryName,Integer pageNum,Integer pageSize){
        Category category = categoryMapper.selectCategoryByName(categoryName);
        String order="created_time";
        switch (orderField){
            case "rate":order="video_rate";break;
            case "playCount":order="video_play_count";break;
            case "time":order="created_time";break;
            default:throw new RuntimeException("UnKown orderType");
        }
        Page page = new Page(pageNum,pageSize);
        page.setOrderColumn(Collections.singletonList(order));
        page.setOrderType("DESC");
        Video codition = new Video();
        codition.setCategoryId(category.getCategoryId());
        PageList<Video> videos = videoMapper.getVideoDetailListLikeName(page,codition);
        PageList<VideoDetailVo> videoDetailVos = new PageList<>();
        videoDetailVos.setPageInfo(videos.getPageInfo());
        videos.forEach(video ->{
            videoDetailVos.add(toVideoDetailVo(video));
        });
        return XPage.wrap(videoDetailVos);
    }

    public XPage<VideoDetailVo> searchVideo(String orderField,String searchKey,
                                            Integer categoryId,Integer pageNum,Integer pageSize){
        String order="created_time";
        switch (orderField){
            case "rate":order="video_rate";break;
            case "playCount":order="video_play_count";break;
            case "time":order="created_time";break;
            default:throw new RuntimeException("UnKown orderType");
        }
        Page page = new Page(pageNum,pageSize);
        page.setOrderColumn(Collections.singletonList(order));
        page.setOrderType("DESC");
        Video codition = new Video();
        codition.setCategoryId(categoryId);
        codition.setVideoTitle(searchKey);
        PageList<Video> videos = videoMapper.getVideoDetailListLikeName(page,codition);
        PageList<VideoDetailVo> videoDetailVos = new PageList<>();
        videoDetailVos.setPageInfo(videos.getPageInfo());
        videos.forEach(video ->{
            videoDetailVos.add(toVideoDetailVo(video));
        });
        return XPage.wrap(videoDetailVos);

    }

    private VideoDetailVo toVideoDetailVo(Video video){
        UserVo userVo = userService.getUserVoByUserId(video.getUploadUserId());
        Picture picture= pictureMapper.selectPictureById(video.getVideoPosterId());
        Category category = categoryMapper.selectCategoryById(video.getCategoryId());
        List<Tag> tagList = tagService.getTagListByVideoId(video.getVideoId());
        return new VideoDetailVo(video.getVideoId(),video.getVideoTitle(),category,video.getCreatedTime(),video.getVideoDesc(),tagList,userVo,video.getVideoPlayCount(),
                video.getVideoCommentPerson(),picture,video.getVideoRate());
    }
}
