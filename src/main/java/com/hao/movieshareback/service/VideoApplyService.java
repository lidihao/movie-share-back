package com.hao.movieshareback.service;

import com.hao.movieshareback.dao.*;
import com.hao.movieshareback.exception.ApplyException;
import com.hao.movieshareback.model.*;
import com.hao.movieshareback.model.type.ApprovalType;
import com.hao.movieshareback.utils.SecurityUtils;
import com.hao.movieshareback.vo.*;
import com.hao.movieshareback.vo.auth.JwtUser;
import com.hao.movieshareback.vo.auth.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class VideoApplyService {

    @Autowired
    private VideoApprovalMapper approvalMapper;

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private PictureMapper pictureMapper;

    @Autowired
    private VideoFileMapper videoFileMapper;

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private EpisodeMapper episodeMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    public XPage<VideoApplyVo> listVideoApply(Integer categoryId, Integer pageNum, Integer pageSize){
        Page page = new Page(pageNum,pageSize);
        PageList<VideoApproval> approvalPageList = approvalMapper.selectVideoApprovalPageList(page,categoryId,ApprovalType.PROCESSING,null);
        PageList<VideoApplyVo> applyVoPageList = new PageList<>();
        applyVoPageList.setPageInfo(approvalPageList.getPageInfo());
        approvalPageList.forEach(videoApproval -> {
            User user = userMapper.getUserByUserId(videoApproval.getUploadUserId());
            Picture picture=pictureMapper.selectPictureById(videoApproval.getPosterId());
            applyVoPageList.add(covertVideoApproval(videoApproval,picture,
                    new UserVo(user.getUserId(),user.getUserName(),null,null,null),null));
        });
        return XPage.wrap(applyVoPageList);
    }

    public VideoApplyVo getVideoApprovalDetail(Integer videoApprovalId){
        VideoApproval videoApproval = approvalMapper.getVideoApproval(videoApprovalId);
        List<Integer> tagIdList = tagMapper.selectTagByVideoApprovalId(videoApprovalId);
        Picture picture = pictureMapper.selectPictureById(videoApproval.getPosterId());
        return covertVideoApproval(videoApproval,picture,null,tagIdList);
    }

    public XPage<VideoFileVo> listVideoFileVo(Integer videoApprovalId,Integer pageSize,Integer pageNum){
        Page page = new Page(pageNum,pageSize);
        PageList<VideoFile> videoFilePageList = videoFileMapper.listVideoFileByApprovalId(page,videoApprovalId);
        PageList<VideoFileVo> videoFileVoPageList = new PageList<>();
        videoFileVoPageList.setPageInfo(videoFilePageList.getPageInfo());
        videoFilePageList.forEach(videoFile -> {
            Picture picture = pictureMapper.selectPictureById(videoFile.getPosterId());
            videoFileVoPageList.add(new VideoFileVo(videoFile.getVideoFileId(),videoFile.getSort(),
                    videoFile.getFileName(),videoFile.getFileUrl(),picture.getUrl(),
                    ApprovalType.getApprovalTypeByTag(videoFile.getApprovalType()).getDesc()));
        });
        return XPage.wrap(videoFileVoPageList);
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void doApplyVideo(VideoApplyActionReceiver videoApplyActionReceiver) throws ApplyException {
        if (hasApply(videoApplyActionReceiver.getVideoApprovalId())){
            throw new ApplyException("申请已经审批");
        }
        ApprovalType approvalType = ApprovalType.getApprovalTypeByTag(videoApplyActionReceiver.getApplystatus());
        if (approvalType==null){
            throw new ApplyException("审批出错");
        }
        approvalMapper.updateApprovalStatus(videoApplyActionReceiver.getVideoApprovalId(),approvalType.getTag(),videoApplyActionReceiver.getRemark());
        videoFileMapper.updateVideoFileApprovalStatus(videoApplyActionReceiver.getVideoApprovalId(),approvalType.getTag());
        if (approvalType==ApprovalType.PASS){
            VideoApproval videoApproval = approvalMapper.getVideoApproval(videoApplyActionReceiver.getVideoApprovalId());
            List<VideoFile> videoFileList = videoFileMapper.listAllVideoFileByApprovalId(videoApplyActionReceiver.getVideoApprovalId());
            List<Integer> tagIdList = tagMapper.selectTagByVideoApprovalId(videoApplyActionReceiver.getVideoApprovalId());
            Video video = new Video(videoApproval.getTitle(),videoApproval.getPosterId(),0L,0L,
                    videoApproval.getIntroduce(),0.0,
                    videoApproval.getUploadUserId(),videoApproval.getCategoryId());
            BaseModel.setNewCreate(video,SecurityUtils.getUsername(),new Date());
            BaseModel.setUpdated(video,SecurityUtils.getUsername(),new Date());
            videoMapper.save(video);
            approvalMapper.relateVideoId(video.getVideoId(),videoApproval.getVideoApprovalId());
            for (VideoFile videoFile:videoFileList){
                Episode episode = new Episode(videoFile.getFileName(),
                        videoFile.getFileUrl(),videoFile.getPosterId(),
                        videoFile.getSort(),video.getVideoId(),videoFile.getVideoFileId());
                episodeMapper.save(episode);
            }
            for (Integer tagId:tagIdList){
                tagMapper.savaTagVideoRelation(tagId,video.getVideoId());
            }


            String queueName="COMPUTE_VIDEO_SIMILARITY_QUEUE";
            redisTemplate.opsForList().leftPush(queueName,video.getVideoId());
        }
    }

    public boolean hasApply(Integer videoApprovalId){
        VideoApproval videoApproval = approvalMapper.getVideoApproval(videoApprovalId);
        ApprovalType approvalType = ApprovalType.getApprovalTypeByTag(videoApproval.getApprovalType());
        if (approvalType==null){
            return false;
        }
        return approvalType!=ApprovalType.PROCESSING;
    }

    public XPage<VideoApplyVo> listUploadVideo(Integer approvalStatus,Integer pageNum,Integer pageSize){
        Page page = new Page(pageNum,pageSize);
        JwtUser jwtUser= (JwtUser)SecurityUtils.getUserDetails();
        UserVo userVo = new UserVo(jwtUser.getUserId(),jwtUser.getUsername(),jwtUser.getAvatarUrl(),jwtUser.getEmail(),null);
        PageList<VideoApproval> videoApprovalPageList = approvalMapper.selectVideoApprovalPageList(page,null,approvalStatus==null?null:ApprovalType.getApprovalTypeByTag(approvalStatus),jwtUser.getUserId());
        PageList<VideoApplyVo> videoApplyVos = new PageList<>();
        videoApplyVos.setPageInfo(videoApprovalPageList.getPageInfo());
        videoApprovalPageList.forEach(videoApproval -> {
            Picture picture=pictureMapper.selectPictureById(videoApproval.getPosterId());
            videoApplyVos.add(covertVideoApproval(videoApproval,picture,
                    userVo,null));
        });
        return XPage.wrap(videoApplyVos);
    }

    private VideoApplyVo covertVideoApproval(VideoApproval videoApproval, Picture picture, UserVo userVo,
                                             List<Integer> tagIdList){
        return new VideoApplyVo(videoApproval.getVideoApprovalId(),videoApproval.getVideoId(),
                videoApproval.getTitle(),picture.getUrl(),videoApproval.getApprovalType(),userVo,videoApproval.getCreatedTime(),
                videoApproval.getCategoryId(),videoApproval.getIntroduce(),videoApproval.getRemark(),tagIdList);
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void doUpdateVideoApproval(VideoApplyUpdateVo videoApplyUpdateVo){
        approvalMapper.updateApprovalStatus(videoApplyUpdateVo.getVideoApprovalId(),ApprovalType.PROCESSING.getTag(),"");
        approvalMapper.updateVideoApprovalMeta(new VideoApproval(videoApplyUpdateVo.getVideoApprovalId(),videoApplyUpdateVo.getPictureId(),videoApplyUpdateVo.getTitle(),videoApplyUpdateVo.getIntroduce()));

        int index = videoFileMapper.getMaxVideoFileIndex(videoApplyUpdateVo.getVideoApprovalId());
        for (Integer videoFileId:videoApplyUpdateVo.getAppendVideoFile()){
            videoFileMapper.relateVideoApproval(videoFileId,videoApplyUpdateVo.getVideoApprovalId(),index++);
        }

        for (Integer videoFileId:videoApplyUpdateVo.getDeleteVideoFile()){
            episodeMapper.deleteEpisodeByVideoFileId(videoFileId);
            videoFileMapper.deleteByVideoFileId(videoFileId);
        }
    }
}
