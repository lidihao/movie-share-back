package com.hao.movieshareback.dao;

import com.hao.movieshareback.model.CommentReply;
import com.hao.movieshareback.vo.CommentReplyVo;
import com.hao.movieshareback.vo.Page;
import com.hao.movieshareback.vo.PageList;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CommentReplyMapper {
    void save(CommentReply commentReply);
    PageList<CommentReply> selectCommentReplyListByCommentId(Page page,Integer videoCommentId);
    CommentReply selectCommentReplyByReplyId(Integer replyId);
}
