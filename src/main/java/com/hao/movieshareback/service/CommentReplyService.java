package com.hao.movieshareback.service;

import com.hao.movieshareback.dao.CommentReplyMapper;
import com.hao.movieshareback.dao.VideoCommentMapper;
import com.hao.movieshareback.dao.VideoMapper;
import com.hao.movieshareback.model.BaseModel;
import com.hao.movieshareback.model.CommentReply;
import com.hao.movieshareback.model.VideoComment;
import com.hao.movieshareback.utils.SecurityUtils;
import com.hao.movieshareback.vo.CommentReplyVo;
import com.hao.movieshareback.vo.Page;
import com.hao.movieshareback.vo.PageList;
import com.hao.movieshareback.vo.XPage;
import com.hao.movieshareback.vo.auth.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CommentReplyService {
    @Autowired
    private CommentReplyMapper commentReplyMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private VideoCommentMapper videoCommentMapper;

    @Transactional
    public void replyComment(CommentReply commentReply){
        commentReply.setReplyDown(0L);
        commentReply.setReplyUp(0L);
        BaseModel.setUpdated(commentReply, SecurityUtils.getUsername(),new Date());
        BaseModel.setNewCreate(commentReply,SecurityUtils.getUsername(),new Date());
        VideoComment videoComment = videoCommentMapper.getCommentById(commentReply.getVideoCommentId());
        videoMapper.incrementVideoCommentPerson(videoComment.getVideoId());
        commentReplyMapper.save(commentReply);
    }

    public XPage<CommentReplyVo> listCommentReplyVo(Integer videoCommentId,Integer pageNum,Integer pageSize){
        Page page = new Page(pageNum,pageSize);
        PageList<CommentReply> commentReplyList = commentReplyMapper.selectCommentReplyListByCommentId(page,videoCommentId);
        PageList<CommentReplyVo> commentReplyVoPageList = new PageList<>();
        commentReplyVoPageList.setPageInfo(commentReplyList.getPageInfo());

        commentReplyList.forEach(commentReply -> {
            CommentReplyVo replyTo = null;
            if (commentReply.getReplyToId()!=-1){
                CommentReply commentReplyRaw = commentReplyMapper.selectCommentReplyByReplyId(commentReply.getReplyToId());
                replyTo=createCommentReplyVo(commentReplyRaw);
            }
            CommentReplyVo replyVo = createCommentReplyVo(commentReply);
            replyVo.setReplyToComment(replyTo);
            commentReplyVoPageList.add(replyVo);
        });
        return XPage.wrap(commentReplyVoPageList);
    }

    private CommentReplyVo createCommentReplyVo(CommentReply commentReply){
        UserVo replyUser = userService.getUserVoByUserId(commentReply.getReplyUserId());
        return new CommentReplyVo(commentReply.getReplyId(),commentReply.getReplyContent(),commentReply.getReplyUp(),
                commentReply.getReplyDown(),replyUser,commentReply.getVideoCommentId(),commentReply.getCreatedTime(),null);
    }
}
