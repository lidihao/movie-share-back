package com.hao.movieshareback.dao;

import com.hao.movieshareback.model.VideoFile;
import com.hao.movieshareback.vo.Page;
import com.hao.movieshareback.vo.PageList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface VideoFileMapper {
    int save(VideoFile videoFile);
    PageList<VideoFile> listVideoFileByApprovalId(Page page,Integer videoApprovalId);
    void relateVideoApproval(@Param("videoFileId") Integer videoFileId, @Param("videoApprovalId") Integer videoApprovalId,@Param("sort") Integer sort);
    void updateVideoFileApprovalStatus(Integer videoApprovalId,Integer approvalType);
    List<VideoFile> listAllVideoFileByApprovalId(Integer videoApprovalId);
    Integer getMaxVideoFileIndex(Integer videoApprovalId);
    void deleteByVideoFileId(Integer videoFileId);
    void setVideoFileInvisible(Integer videoFileId);
    List<VideoFile> getInvisibleVideoFile(Integer videoApprovalId);
    VideoFile getVideoFileDetail(Integer videoFileId);
}
