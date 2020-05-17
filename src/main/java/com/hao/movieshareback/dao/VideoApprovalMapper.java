package com.hao.movieshareback.dao;

import com.hao.movieshareback.model.VideoApproval;
import com.hao.movieshareback.model.type.ApprovalType;
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
    PageList<VideoApproval> selectVideoApprovalPageList(Page page, @Param("categoryId")Integer categoryId, ApprovalType approvalType,Integer userId);
    VideoApproval getVideoApproval(Integer videoApprovalId);
    void updateApprovalStatus(Integer videoApprovalId,Integer approvalType,String remark);
    void updateVideoApprovalMeta(VideoApproval videoApproval);
    void relateVideoId(Integer videoId,Integer videoApprovalId);
    void deleteVideoApproval(Integer videoApprovalId);
}
