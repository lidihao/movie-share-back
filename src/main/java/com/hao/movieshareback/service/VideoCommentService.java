package com.hao.movieshareback.service;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.hao.movieshareback.dao.RateVideoCommentMapper;
import com.hao.movieshareback.dao.SystemMessageMapper;
import com.hao.movieshareback.dao.VideoCommentMapper;
import com.hao.movieshareback.dao.VideoMapper;
import com.hao.movieshareback.model.*;
import com.hao.movieshareback.model.bo.RateCommentMessage;
import com.hao.movieshareback.model.bo.VideoCommentMessage;
import com.hao.movieshareback.service.message.MessageConvert;
import com.hao.movieshareback.service.message.MessageConvertRegistry;
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

    @Autowired
    private MessageConvertRegistry messageConvertRegistry;

    @Autowired
    private SystemMessageMapper systemMessageMapper;

    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public void commentVideo(VideoComment videoComment){
        videoComment.setCommentDown(0L);
        videoComment.setCommentUp(0L);
        BaseModel.setUpdated(videoComment, SecurityUtils.getUsername(),new Date());
        BaseModel.setNewCreate(videoComment,SecurityUtils.getUsername(),new Date());
        videoMapper.incrementVideoCommentPerson(videoComment.getVideoId());
        videoCommentMapper.save(videoComment);

        //发送消息提醒
        MessageConvert messageConvert=messageConvertRegistry.getMessageConvert(VideoCommentMessage.class);
        UserVo commentUser = userService.getUserVoByUserId(videoComment.getCommentUserId());
        Video video = videoMapper.getVideo(videoComment.getVideoId());
        VideoCommentMessage videoCommentMessage = new VideoCommentMessage(videoComment.getCommentContent(),video,commentUser);
        if (messageConvert!=null){
            SystemMessage systemMessage = messageConvert.convertMessage(videoCommentMessage);
            BaseModel.setUpdated(systemMessage, SecurityUtils.getUsername(),new Date());
            BaseModel.setNewCreate(systemMessage,SecurityUtils.getUsername(),new Date());
            systemMessageMapper.save(systemMessage);
        }
    }

    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public synchronized void rateVideo(RateVideoComment rateVideoComment){
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
        videoMapper.incrementVideoCommentPerson(rateVideoComment.getVideoId());
        videoMapper.updateRate(newRate,rateVideoComment.getVideoId(),new Date(),SecurityUtils.getUsername());
        rateVideoCommentMapper.save(rateVideoComment);

        MessageConvert messageConvert=messageConvertRegistry.getMessageConvert(RateCommentMessage.class);
        Video video = videoMapper.getVideo(rateVideoComment.getVideoId());
        RateCommentMessage rateCommentMessage = new RateCommentMessage(rateVideoComment.getCommentUserId(),
                video,rateVideoComment.getCommentContent(),rateVideoComment.getRate());
        if (messageConvert!=null){
            SystemMessage systemMessage = messageConvert.convertMessage(rateCommentMessage);
            BaseModel.setUpdated(systemMessage, SecurityUtils.getUsername(),new Date());
            BaseModel.setNewCreate(systemMessage,SecurityUtils.getUsername(),new Date());
            systemMessageMapper.save(systemMessage);
        }
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

    public void deleteVideoComment(Integer videoId){

    }

    public void deleteVideoRateComment(Integer videoId){

    }


    public boolean hasRateComment(Integer videoId,Integer userId){
        return rateVideoCommentMapper.getCommentCountByUserIdAndVideoId(videoId,userId)!=0;
    }

}
