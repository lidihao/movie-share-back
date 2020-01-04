package com.hao.movieshareback.dao;

import com.hao.movieshareback.model.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TagMapper {
    List<Tag> selectAllTag();
    void saveTagVideoApprovalRelation(@Param("tagId")Integer tagId,@Param("videoApprovalId")Integer videoApprovalId);
}
