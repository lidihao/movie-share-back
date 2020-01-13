package com.hao.movieshareback.service;

import com.hao.movieshareback.dao.PictureMapper;
import com.hao.movieshareback.dao.TagMapper;
import com.hao.movieshareback.dao.VideoApprovalMapper;
import com.hao.movieshareback.dao.VideoFileMapper;
import com.hao.movieshareback.exception.ApplyException;
import com.hao.movieshareback.model.Picture;
import com.hao.movieshareback.model.VideoApproval;
import com.hao.movieshareback.model.VideoFile;
import com.hao.movieshareback.model.type.ApprovalType;
import com.hao.movieshareback.utils.SecurityUtils;
import com.hao.movieshareback.vo.*;
import com.hao.movieshareback.vo.auth.JwtUser;
import com.hao.movieshareback.vo.auth.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    public XPage<VideoApplyVo> listVideoApply(Integer categoryId, Integer pageNum, Integer pageSize){
        JwtUser jwtUser = (JwtUser) SecurityUtils.getUserDetails();
        UserVo userVo = new UserVo(jwtUser.getUsername(),jwtUser.getAvatarUrl(),jwtUser.getEmail(),null);
        Page page = new Page(pageNum,pageSize);
        PageList<VideoApproval> approvalPageList = approvalMapper.selectVideoApprovalPageList(page,categoryId);
        PageList<VideoApplyVo> applyVoPageList = new PageList<>();
        applyVoPageList.setPageInfo(approvalPageList.getPageInfo());
        approvalPageList.forEach(videoApproval -> {
            Picture picture=pictureMapper.selectPictureById(videoApproval.getPosterId());
            applyVoPageList.add(covertVideoApproval(videoApproval,picture,userVo,null));
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
        approvalMapper.updateApprovalStatus(videoApplyActionReceiver.getVideoApprovalId(),approvalType.getTag());
        videoFileMapper.updateVideoFileApprovalStatus(videoApplyActionReceiver.getVideoApprovalId(),approvalType.getTag());
        if (approvalType==ApprovalType.PASS){

        }
    }

    public boolean hasApply(Integer videoApprovalId){
        return false;
    }

    private VideoApplyVo covertVideoApproval(VideoApproval videoApproval, Picture picture, UserVo userVo,
                                             List<Integer> tagIdList){
        return new VideoApplyVo(videoApproval.getVideoApprovalId(),videoApproval.getVideoId(),
                videoApproval.getTitle(),picture.getUrl(),userVo,videoApproval.getCreatedTime(),
                videoApproval.getCategoryId(),videoApproval.getIntroduce(),tagIdList);
    }
}
