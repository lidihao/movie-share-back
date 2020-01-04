package com.hao.movieshareback.service;

import com.hao.movieshareback.dao.TagMapper;
import com.hao.movieshareback.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TagService {
    @Autowired
    private TagMapper tagMapper;

    public List<Tag> listTag(){
        return tagMapper.selectAllTag();
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void relatedTagAndApproval(List<Integer> tagList,Integer videoApprovalId){
        for (Integer tagId:tagList){
            tagMapper.saveTagVideoApprovalRelation(tagId,videoApprovalId);
        }
    }
}
