package com.hao.movieshareback.dao;

import com.hao.movieshareback.model.Video;
import com.hao.movieshareback.vo.Page;
import com.hao.movieshareback.vo.PageList;
import com.hao.movieshareback.vo.VideoIndexVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface VideoMapper {
    int save(Video video);
    Video getVideo(Integer videoId);
    List<Video> getAllVideo();
    PageList<Video> getVideoDetailListLikeName(Page page,Video video);
    void updateRate(Double rate, Integer videoId, Date updateTime,String userName);
    void incrementVideoPlayCount(Integer videoId);
    void incrementVideoCommentPerson(Integer videoId);
    PageList<Video> getFavoriteVideoDetailList(Page page,Video video,Integer userId);
    PageList<VideoIndexVo> selectVideoIndexVo(Page page, String curDateTime);
}
