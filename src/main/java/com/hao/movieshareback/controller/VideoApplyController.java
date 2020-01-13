package com.hao.movieshareback.controller;

import com.hao.movieshareback.annotation.auth.AnonymousAccess;
import com.hao.movieshareback.service.VideoApplyService;
import com.hao.movieshareback.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    }
}
