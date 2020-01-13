package com.hao.movieshareback.dao;

import com.hao.movieshareback.model.VideoApproval;
import com.hao.movieshareback.vo.Page;
import com.hao.movieshareback.vo.PageList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface VideoApprovalMapper {
    int save(VideoApproval videoApproval);
    PageList<VideoApproval> selectVideoApprovalPageList(Page page, @Param("categoryId")Integer categoryId);
    VideoApproval getVideoApproval(Integer videoApprovalId);
    int updateApprovalStatus(Integer videoApproval,Integer approvalType);
}
