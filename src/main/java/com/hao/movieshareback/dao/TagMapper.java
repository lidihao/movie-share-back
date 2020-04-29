package com.hao.movieshareback.dao;

import com.hao.movieshareback.model.Tag;
import com.hao.movieshareback.vo.TagIdCountMap;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface TagMapper {
    List<Tag> selectAllTag();
    List<Integer> selectTagByVideoApprovalId(Integer videoApprovalId);
    void saveTagVideoApprovalRelation(@Param("tagId")Integer tagId,@Param("videoApprovalId")Integer videoApprovalId);
    void savaTagVideoRelation(@Param("tagId")Integer tagId,@Param("videoId")Integer videoId);
    List<Integer> selectTagByVideoId(Integer videoId);
    Tag selectTagByTagId(Integer tagId);
    List<TagIdCountMap> selectTagCountList();
}
