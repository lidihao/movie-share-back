package com.hao.movieshareback.service;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.hao.movieshareback.dao.RateVideoCommentMapper;
import com.hao.movieshareback.dao.VideoCommentMapper;
import com.hao.movieshareback.dao.VideoMapper;
import com.hao.movieshareback.model.BaseModel;
import com.hao.movieshareback.model.RateVideoComment;
import com.hao.movieshareback.model.Video;
import com.hao.movieshareback.model.VideoComment;
import com.hao.movieshareback.utils.SecurityUtils;
import com.hao.movieshareback.vo.*;
import com.hao.movieshareback.vo.auth.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VideoCommentService {

    @Autowired
    private VideoCommentMapper videoCommentMapper;

    @Autowired
    private RateVideoCommentMapper rateVideoCommentMapper;

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private UserService userService;


    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public void commentVideo(VideoComment videoComment){
        videoComment.setCommentDown(0L);
        videoComment.setCommentUp(0L);
        BaseModel.setUpdated(videoComment, SecurityUtils.getUsername(),new Date());
        BaseModel.setNewCreate(videoComment,SecurityUtils.getUsername(),new Date());
        videoCommentMapper.save(videoComment);
    }

    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public void rateVideo(RateVideoComment rateVideoComment){
        if (hasRateComment(rateVideoComment.getVideoId(),rateVideoComment.getCommentUserId())){
            throw new RuntimeException("已经给视频评分");
        }
        rateVideoComment.setCommentDown(0L);
        rateVideoComment.setCommentUp(0L);
        BaseModel.setUpdated(rateVideoComment, SecurityUtils.getUsername(),new Date());
        BaseModel.setNewCreate(rateVideoComment,SecurityUtils.getUsername(),new Date());
        Integer rateCount=rateVideoCommentMapper.getCommentCountByVideoId(rateVideoComment.getVideoId());
        Double oldRate=videoMapper.getVideo(rateVideoComment.getVideoId()).getVideoRate();
        Double newRate= (oldRate*rateCount+rateVideoComment.getRate())/(rateCount+1);
        videoMapper.updateRate(newRate,rateVideoComment.getVideoId());
        rateVideoCommentMapper.save(rateVideoComment);
    }

    public XPage<VideoCommentVo> listVideoCommentByVideoId(Integer videoId,Integer pageNum,Integer pageSize){
        Page page = new Page(pageNum,pageSize);
        PageList<VideoComment> videoCommentList=videoCommentMapper.selectCommentListByVideoId(page,videoId);
        PageList<VideoCommentVo> videoCommentVoPageList= new PageList<>();
        videoCommentVoPageList.setPageInfo(videoCommentList.getPageInfo());
        videoCommentList.forEach(videoComment -> {
            UserVo userVo = userService.getUserVoByUserId(videoComment.getCommentUserId());
            videoCommentVoPageList.add(
                    new VideoCommentVo(videoComment.getCommentId(),videoComment.getCommentContent(),videoComment.getCommentUp(),
                            videoComment.getCommentDown(),videoComment.getVideoId(),userVo,videoComment.getCreatedTime())
            );
        });
        return XPage.wrap(videoCommentVoPageList);
    }

    public XPage<RateCommentVo> listRateVideoComment(Integer videoId,Integer pageNum,Integer pageSize){
        Page page = new Page(pageNum,pageSize);
        PageList<RateVideoComment> rateVideoComments = rateVideoCommentMapper.selectCommentListByVideoId(page,videoId);
        PageList<RateCommentVo> rateCommentVoPage = new PageList<>();
        rateCommentVoPage.setPageInfo(rateVideoComments.getPageInfo());
        rateVideoComments.forEach(rateVideoComment -> {
            UserVo userVo = userService.getUserVoByUserId(rateVideoComment.getCommentUserId());
            rateCommentVoPage.add(
                    new RateCommentVo(rateVideoComment.getCommentId(),rateVideoComment.getCommentContent(),
                            rateVideoComment.getCommentUp(),rateVideoComment.getCommentDown(),rateVideoComment.getVideoId(),
                            userVo,rateVideoComment.getCreatedTime(),rateVideoComment.getRate()
                            )
            );
        });
        return XPage.wrap(rateCommentVoPage);
    }


    public boolean hasRateComment(Integer videoId,Integer userId){
        return rateVideoCommentMapper.getCommentCountByUserIdAndVideoId(videoId,userId)!=0;
    }

}
