package com.hao.movieshareback.service;

import com.hao.movieshareback.dao.PictureMapper;
import com.hao.movieshareback.dao.VideoApprovalMapper;
import com.hao.movieshareback.model.Picture;
import com.hao.movieshareback.model.VideoApproval;
import com.hao.movieshareback.utils.SecurityUtils;
import com.hao.movieshareback.vo.Page;
import com.hao.movieshareback.vo.PageList;
import com.hao.movieshareback.vo.VideoApplyVo;
import com.hao.movieshareback.vo.XPage;
import com.hao.movieshareback.vo.auth.JwtUser;
import com.hao.movieshareback.vo.auth.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoApplyService {

    @Autowired
    private VideoApprovalMapper approvalMapper;

    @Autowired
    private PictureMapper pictureMapper;

    public XPage<VideoApplyVo> listVideoApply(Integer categoryId, Integer pageNum, Integer pageSize){
        JwtUser jwtUser = (JwtUser) SecurityUtils.getUserDetails();
        UserVo userVo = new UserVo(jwtUser.getUsername(),jwtUser.getAvatarUrl(),jwtUser.getEmail(),null);
        Page page = new Page(pageNum,pageSize);
        PageList<VideoApproval> approvalPageList = approvalMapper.selectVideoApprovalPageList(page,categoryId);
        PageList<VideoApplyVo> applyVoPageList = new PageList<>();
        applyVoPageList.setPageInfo(approvalPageList.getPageInfo());
        approvalPageList.forEach(videoApproval -> {
            Picture picture=pictureMapper.selectPictureById(videoApproval.getPosterId());
            applyVoPageList.add(covertVideoApproval(videoApproval,picture,userVo));
        });
        return XPage.wrap(applyVoPageList);
    }

    private VideoApplyVo covertVideoApproval(VideoApproval videoApproval, Picture picture, UserVo userVo){
        return new VideoApplyVo(videoApproval.getVideoApprovalId(),videoApproval.getVideoId(),
                videoApproval.getTitle(),picture.getUrl(),userVo,videoApproval.getCreatedTime());
    }
}
