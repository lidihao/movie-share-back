package com.hao.movieshareback.dao;

import com.hao.movieshareback.model.VideoComment;
import com.hao.movieshareback.vo.Page;
import com.hao.movieshareback.vo.PageList;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface VideoCommentMapper {
    void save(VideoComment videoComment);
    PageList<VideoComment> selectCommentListByVideoId(Page page, Integer videoId);
    Integer getCommentCountByVideoId(Integer videoId);
}
