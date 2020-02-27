package com.hao.movieshareback.dao;

import com.hao.movieshareback.model.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import sun.security.krb5.internal.TGSRep;

import java.util.List;

@Repository
@Mapper
public interface TagMapper {
    List<Tag> selectAllTag();
    List<Integer> selectTagByVideoApprovalId(Integer videoApprovalId);
    void saveTagVideoApprovalRelation(@Param("tagId")Integer tagId,@Param("videoApprovalId")Integer videoApprovalId);
    void savaTagVideoRelation(@Param("tagId")Integer tagId,@Param("videoId")Integer videoId);
    List<Integer> selectTagByVideoId(Integer videoId);
    Tag selectTagByTagId(Integer tagId);
}
