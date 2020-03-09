package com.hao.movieshareback.service;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.hao.movieshareback.dao.VideoCommentMapper;
import com.hao.movieshareback.dao.VideoMapper;
import com.hao.movieshareback.model.BaseModel;
import com.hao.movieshareback.model.Video;
import com.hao.movieshareback.model.VideoComment;
import com.hao.movieshareback.utils.SecurityUtils;
import com.hao.movieshareback.vo.Page;
import com.hao.movieshareback.vo.PageList;
import com.hao.movieshareback.vo.VideoCommentVo;
import com.hao.movieshareback.vo.XPage;
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
    private VideoMapper videoMapper;

    @Autowired
    private UserService userService;


    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public void commentVideo(VideoComment videoComment){
        videoComment.setCommentDown(0L);
        videoComment.setCommentUp(0L);
        BaseModel.setUpdated(videoComment, SecurityUtils.getUsername(),new Date());
        BaseModel.setNewCreate(videoComment,SecurityUtils.getUsername(),new Date());

        Integer commentCount=videoCommentMapper.getCommentCountByVideoId(videoComment.getVideoId());
        Video video = videoMapper.getVideo(videoComment.getVideoId());
        Double rate = (video.getVideoRate()*commentCount+videoComment.getRate())/(commentCount+1);
        videoMapper.updateRate(rate,videoComment.getVideoId());
        videoCommentMapper.save(videoComment);
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
                            videoComment.getCommentDown(),videoComment.getRate(),videoComment.getVideoId(),userVo,videoComment.getCreatedTime())
            );
        });
        return XPage.wrap(videoCommentVoPageList);
    }
}
