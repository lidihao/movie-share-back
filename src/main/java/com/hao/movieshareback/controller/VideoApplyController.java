package com.hao.movieshareback.controller;

import com.hao.movieshareback.annotation.auth.AnonymousAccess;
import com.hao.movieshareback.service.VideoApplyService;
import com.hao.movieshareback.vo.ResultBody;
import com.hao.movieshareback.vo.VideoApplyVo;
import com.hao.movieshareback.vo.XPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
