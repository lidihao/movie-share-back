package com.hao.movieshareback.dao;

import com.hao.movieshareback.model.VideoFile;
import com.hao.movieshareback.vo.Page;
import com.hao.movieshareback.vo.PageList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface VideoFileMapper {
    int save(VideoFile videoFile);
    PageList<VideoFile> listVideoFileByApprovalId(Page page,Integer videoApprovalId);
    void relateVideoApproval(@Param("videoFileId") Integer videoFileId, @Param("videoApprovalId") Integer videoApprovalId,@Param("sort") Integer sort);
    int updateVideoFileApprovalStatus(Integer videoApprovalId,Integer approvalType);
}
