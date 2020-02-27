package com.hao.movieshareback.controller;

import com.hao.movieshareback.exception.ApplyException;
import com.hao.movieshareback.model.type.ApprovalType;
import com.hao.movieshareback.service.VideoApplyService;
import com.hao.movieshareback.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/videoApply")
@RestController
public class VideoApplyController {

    @Autowired
    private VideoApplyService applyService;

    /**
     * @TODO管理员权限
     */
    @GetMapping("/list")
    public ResultBody getVideoApply(Integer categoryId,Integer pageNum,Integer pageSize){
        if (pageNum==null){
            pageNum=1;
        }
        if (pageSize==null){
            pageSize=10;
        }
        XPage<VideoApplyVo> videoApplyVoXPage = applyService.listVideoApply(categoryId,pageNum,pageSize);
        return ResultBody.success(videoApplyVoXPage);
    }

    @GetMapping("/applyDetail/{videoApprovalId}")
    public ResultBody getVideoApplyDetail(@PathVariable("videoApprovalId")Integer videoApprovalId){
        VideoApplyVo videoApplyVo = applyService.getVideoApprovalDetail(videoApprovalId);
        return ResultBody.success(videoApplyVo);
    }

    @GetMapping("/applyDetail/videoFileList")
    public ResultBody listVideoFile(Integer videoApprovalId,Integer pageSize,Integer pageNum){
        if (pageNum==null){
            pageNum=1;
        }
        if (pageSize==null){
            pageSize=10;
        }
        XPage<VideoFileVo> videoFileVoList = applyService.listVideoFileVo(videoApprovalId,pageSize,pageNum);
        return ResultBody.success(videoFileVoList);
    }

    @PostMapping("/doApplyVideo")
    public ResultBody doApplyVideo(@RequestBody VideoApplyActionReceiver videoApplyActionReceiver){
        try {
            applyService.doApplyVideo(videoApplyActionReceiver);
        }catch (ApplyException e){
            e.printStackTrace();
            return ResultBody.error(e.getMessage());
        }
        return ResultBody.success();
    }

    @GetMapping("/listVideoApprovalTag")
    public ResultBody getVideoApprovalTag(){
        ApprovalType[] approvalTypes = ApprovalType.values();
        List<ApprovalStatusVo> approvalStatusVoList = new ArrayList<>(approvalTypes.length);
        for (int i=0;i<approvalTypes.length;i++){
            approvalStatusVoList.add(new ApprovalStatusVo(approvalTypes[i].getTag(),approvalTypes[i].getDesc()));
        }
        return ResultBody.success(approvalStatusVoList);
    }
    @GetMapping("/listUploadVideo")
    public ResultBody listUploadVideo(Integer approvalStatus,Integer pageNum,Integer pageSize){

        XPage<VideoApplyVo> videoApplyVoXPage = applyService.listUploadVideo(approvalStatus,pageNum,pageSize);
        return ResultBody.success(videoApplyVoXPage);
    }

    @PutMapping("/update")
    public ResultBody updateVideoApply(@RequestBody VideoApplyUpdateVo videoApplyUpdateVo){
        try {
            applyService.doUpdateVideoApproval(videoApplyUpdateVo);
        }catch (ApplyException e){
            e.printStackTrace();
            return ResultBody.error(e.getMessage());
        }
        return ResultBody.success();
    }
}
