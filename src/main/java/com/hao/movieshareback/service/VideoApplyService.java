package com.hao.movieshareback.service;

import com.google.common.collect.Sets;
import com.hao.movieshareback.config.SystemConst;
import com.hao.movieshareback.dao.*;
import com.hao.movieshareback.exception.ApplyException;
import com.hao.movieshareback.model.*;
import com.hao.movieshareback.model.bo.VideoApplyMessage;
import com.hao.movieshareback.model.bo.VideoUploadMessage;
import com.hao.movieshareback.model.type.ApprovalType;
import com.hao.movieshareback.service.message.MessageConvert;
import com.hao.movieshareback.service.message.MessageConvertRegistry;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private VideoFileService videoFileService;

    @Autowired
    private VideoService videoService;

    @Autowired
    private MessageConvertRegistry messageConvertRegistry;

    @Autowired
    private SystemMessageMapper systemMessageMapper;


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
        VideoApproval videoApproval = approvalMapper.getVideoApproval(videoApplyActionReceiver.getVideoApprovalId());
        List<VideoFile> videoFileList = videoFileMapper.listAllVideoFileByApprovalId(videoApplyActionReceiver.getVideoApprovalId());
        List<Integer> tagIdList = tagMapper.selectTagByVideoApprovalId(videoApplyActionReceiver.getVideoApprovalId());
        Video video =null;
        //不通过
        approvalMapper.updateApprovalStatus(videoApplyActionReceiver.getVideoApprovalId(),approvalType.getTag(),videoApplyActionReceiver.getRemark());
        videoFileMapper.updateVideoFileApprovalStatus(videoApplyActionReceiver.getVideoApprovalId(),approvalType.getTag());
        // 重新审批退回的申请
        if (approvalType==ApprovalType.PASS) {
            //第一次审批
            if (videoApproval.getVideoId() == -1) {
                 video=new Video(videoApproval.getTitle(), videoApproval.getPosterId(), 0L, 0L,
                        videoApproval.getIntroduce(), 0.0,
                        videoApproval.getUploadUserId(), videoApproval.getCategoryId());
                BaseModel.setNewCreate(video, SecurityUtils.getUsername(), new Date());
                BaseModel.setUpdated(video, SecurityUtils.getUsername(), new Date());
                videoMapper.save(video);
                //将视频与视频申请进行关联
                approvalMapper.relateVideoId(video.getVideoId(), videoApproval.getVideoApprovalId());
                for (VideoFile videoFile : videoFileList) {
                    Episode episode = new Episode(videoFile.getFileName(),
                            videoFile.getFileUrl(), videoFile.getPosterId(),
                            videoFile.getSort(), video.getVideoId(), videoFile.getVideoFileId());
                    episodeMapper.save(episode);
                }
                for (Integer tagId : tagIdList) {
                    tagMapper.savaTagVideoRelation(tagId, video.getVideoId());
                }
                //删除不可见的VideoFile
                List<VideoFile> videoFiles = videoFileMapper.getInvisibleVideoFile(videoApproval.getVideoApprovalId());
                videoFiles.forEach(videoFile -> {
                    videoFileService.deleteVideoFile(videoFile.getVideoFileId());
                });

                //计算视频相关推荐
                String queueName = SystemConst.VIDEO_SIMILARITY_QUEUE_NAME;
                redisTemplate.opsForList().leftPush(queueName, video.getVideoId());

                //发送提醒消息
                List<User> followingUserList = userMapper.getAllFollowedUserList(video.getUploadUserId());
                MessageConvert messageConvert = messageConvertRegistry.getMessageConvert(VideoUploadMessage.class);
                UserVo userVo = userService.getUserVoByUserId(video.getUploadUserId());
                if (followingUserList!=null){
                    for (User followingUser:followingUserList) {
                        VideoUploadMessage videoUploadMessage = new VideoUploadMessage(userVo,followingUser.getUserId(),video);
                        SystemMessage systemMessage = messageConvert.convertMessage(videoUploadMessage);
                        BaseModel.setUpdated(systemMessage, SecurityUtils.getUsername(),new Date());
                        BaseModel.setNewCreate(systemMessage,SecurityUtils.getUsername(),new Date());
                        systemMessageMapper.save(systemMessage);
                    }
                }
            }else {
                video=videoMapper.getVideo(videoApproval.getVideoId());
                List<Episode> episodeList = episodeMapper.getEpisodeByVideoId(video.getVideoId());
                videoMapper.updateVideoMeta(videoApproval.getTitle(),videoApproval.getIntroduce(),videoApproval.getPosterId(),video.getVideoId());
                Set<Integer> oldEpisode=episodeList.stream().map(Episode::getVideoFileId).collect(Collectors.toSet());
                Set<Integer> newEpisode=videoFileList.stream().map(VideoFile::getVideoFileId).collect(Collectors.toSet());
                List<VideoFile> videoFiles = videoFileMapper.getInvisibleVideoFile(videoApproval.getVideoApprovalId());

                Set<Integer> deleteSet=Sets.difference(oldEpisode,newEpisode);
                Set<Integer> addSet=Sets.difference(newEpisode,oldEpisode);

                Set<Integer> readableDeleteSet = new HashSet<>(deleteSet);

                videoFiles.forEach(videoFile -> {
                    readableDeleteSet.add(videoFile.getVideoFileId());
                });

                for (Integer deletedVideoFileId:readableDeleteSet){
                    episodeMapper.deleteEpisodeByVideoFileId(deletedVideoFileId);
                    videoFileService.deleteVideoFile(deletedVideoFileId);
                }

                for (Integer addVideoFileId:addSet){
                    VideoFile addVideoFileDetail=videoFileMapper.getVideoFileDetail(addVideoFileId);
                    Episode episode = new Episode(addVideoFileDetail.getFileName(),
                            addVideoFileDetail.getFileUrl(), addVideoFileDetail.getPosterId(),
                            addVideoFileDetail.getSort(), video.getVideoId(), addVideoFileDetail.getVideoFileId());
                    episodeMapper.save(episode);
                }
            }
        }
        //消息通知
        MessageConvert messageConvert = messageConvertRegistry.getMessageConvert(VideoApplyMessage.class);
        VideoApplyMessage videoApplyMessage = new VideoApplyMessage(video,videoApproval,approvalType);
        if (messageConvert!=null){
            SystemMessage systemMessage = messageConvert.convertMessage(videoApplyMessage);
            BaseModel.setUpdated(systemMessage, SecurityUtils.getUsername(),new Date());
            BaseModel.setNewCreate(systemMessage,SecurityUtils.getUsername(),new Date());
            systemMessageMapper.save(systemMessage);
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
            videoFileMapper.setVideoFileInvisible(videoFileId);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteVideoApproval(Integer videoApprovalId){
        VideoApproval videoApproval = approvalMapper.getVideoApproval(videoApprovalId);
        JwtUser user= (JwtUser) SecurityUtils.getUserDetails();
        if (!videoApproval.getUploadUserId().equals(user.getUserId())){
            return;
        }

        List<VideoFile> videoFileList = videoFileMapper.listAllVideoFileByApprovalId(videoApprovalId);
        videoFileList.forEach(videoFile -> {
            videoFileService.deleteVideoFile(videoFile.getVideoFileId());
        });
        if (videoApproval.getVideoId()!=-1) {
            videoService.deleteVideoClearly(videoApproval.getVideoId());
        }
        approvalMapper.deleteVideoApproval(videoApprovalId);
    }
}
