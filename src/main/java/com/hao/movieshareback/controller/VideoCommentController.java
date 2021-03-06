package com.hao.movieshareback.controller;

import com.hao.movieshareback.annotation.auth.AnonymousAccess;
import com.hao.movieshareback.annotation.log.Log;
import com.hao.movieshareback.model.CommentReply;
import com.hao.movieshareback.model.RateVideoComment;
import com.hao.movieshareback.model.VideoComment;
import com.hao.movieshareback.service.CommentReplyService;
import com.hao.movieshareback.service.VideoCommentService;
import com.hao.movieshareback.vo.ResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/video/comment")
@RestController
public class VideoCommentController {

    @Autowired
    private VideoCommentService videoCommentService;

    @Autowired
    private CommentReplyService commentReplyService;

    @Log(value = "comment_video",businessType = "comment_video")
    @PostMapping("/doComment")
    public ResultBody doComment(@RequestBody VideoComment videoComment){
        videoCommentService.commentVideo(videoComment);
        return ResultBody.success("success");
    }

    @PostMapping("/doRateComment")
    public ResultBody doRateComment(@RequestBody RateVideoComment rateVideoComment){
        try {
            videoCommentService.rateVideo(rateVideoComment);
            return ResultBody.success("success");
        }catch (Exception e){
            return ResultBody.error(e.getMessage());
        }
    }


    @AnonymousAccess
    @GetMapping("/listComment")
    public ResultBody listVideoComment(Integer videoId,Integer pageNum,Integer pageSize){
        return ResultBody.success(videoCommentService.listVideoCommentByVideoId(videoId,pageNum,pageSize));
    }

    @AnonymousAccess
    @GetMapping("/listRateComment")
    public ResultBody listRateComment(Integer videoId,Integer pageNum,Integer pageSize){
        return ResultBody.success(videoCommentService.listRateVideoComment(videoId,pageNum,pageSize));
    }


    @PostMapping("/replyComment")
    public ResultBody replyComment(@RequestBody CommentReply commentReply){
        commentReplyService.replyComment(commentReply);
        return ResultBody.success("success");
    }

    @AnonymousAccess
    @GetMapping("/listReply")
    public ResultBody listCommentReply(Integer commentId,Integer pageNum,Integer pageSize){
        return ResultBody.success(commentReplyService.listCommentReplyVo(commentId,pageNum,pageSize));
    }
}
