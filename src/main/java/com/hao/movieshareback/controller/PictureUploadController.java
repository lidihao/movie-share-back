package com.hao.movieshareback.controller;

import com.hao.movieshareback.model.Picture;
import com.hao.movieshareback.model.UserSkin;
import com.hao.movieshareback.service.PictureService;
import com.hao.movieshareback.service.PictureUploadService;
import com.hao.movieshareback.vo.PictureVo;
import com.hao.movieshareback.vo.ResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/addSkin")
    public ResultBody addSkin(@RequestBody UserSkin userSkin){
        pictureUploadService.addUserSkin(userSkin);
        return ResultBody.success();
    }

    @GetMapping("/skinList")
    public ResultBody getSkinList(Integer userId){
        return ResultBody.success(pictureUploadService.getUserSkinList(userId));
    }

    @PutMapping("/updateSkin")
    public ResultBody updateUserSkin(@RequestBody UserSkin userSkin){
        pictureUploadService.updateSkin(userSkin);
        return ResultBody.success();
    }

    @PostMapping("/deleteSkin")
    public ResultBody deleteSkin(@RequestBody UserSkin userSkin){
        try {
            pictureUploadService.deleteSkin(userSkin);
            return ResultBody.success();
        }catch (Exception e){
            return ResultBody.error(e.getMessage());
        }
    }
}
