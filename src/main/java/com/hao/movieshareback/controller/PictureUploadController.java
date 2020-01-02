package com.hao.movieshareback.controller;

import com.hao.movieshareback.model.Picture;
import com.hao.movieshareback.service.PictureService;
import com.hao.movieshareback.service.PictureUploadService;
import com.hao.movieshareback.vo.PictureVo;
import com.hao.movieshareback.vo.ResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/picUpload")
@RestController
public class PictureUploadController {

    @Autowired
    private PictureUploadService pictureUploadService;

    @PostMapping("")
    public ResultBody uploadImg(PictureVo pictureVo){
        try {
            Picture picture=pictureUploadService.saveUploadPic(pictureVo);
            return ResultBody.success(picture);
        }catch (Exception e){
            e.printStackTrace();
            return ResultBody.error("上传图片出错");
        }
    }
}
