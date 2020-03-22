package com.hao.movieshareback.dao;

import com.hao.movieshareback.model.RateVideoComment;
import com.hao.movieshareback.vo.Page;
import com.hao.movieshareback.vo.PageList;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RateVideoCommentMapper {
    void save(RateVideoComment rateVideoComment);
    PageList<RateVideoComment> selectCommentListByVideoId(Page page, Integer videoId);
    Integer getCommentCountByVideoId(Integer videoId);
    Integer getCommentCountByUserIdAndVideoId(Integer videoId,Integer userId);
}
