package com.hao.movieshareback.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.socket.aio.IoAction;
import com.hao.movieshareback.dao.PictureMapper;
import com.hao.movieshareback.dao.UserMapper;
import com.hao.movieshareback.model.Picture;
import com.hao.movieshareback.model.User;
import com.hao.movieshareback.model.UserSkin;
import com.hao.movieshareback.utils.FileUtil;
import com.hao.movieshareback.utils.SecurityUtils;
import com.hao.movieshareback.vo.PictureVo;
import com.hao.movieshareback.vo.auth.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Service
public class PictureUploadService {

    @Value("${pic.upload.root}")
    private String picRoot;

    @Value("${pic.upload.url.root}")
    private String urlRoot;

    @Autowired
    private PictureMapper pictureMapper;

    @Autowired
    private UserMapper userMapper;

    public Picture saveUploadPic(PictureVo pictureVo) throws IOException {
        File dir = new File(picRoot);
        String suffix = FileUtil.getExtensionName(pictureVo.getFileName());
        String fileName = IdUtil.simpleUUID();
        File img = File.createTempFile(fileName,suffix,dir);

        pictureVo.getFile().transferTo(img);
        String url=urlRoot+img.getName();
        Picture picture = new Picture(pictureVo.getFileName(),pictureVo.getHeight(),pictureVo.getSize(),pictureVo.getWidth(),url);
        try {
            pictureMapper.save(picture);
        }catch (Exception e){
            e.printStackTrace();
            if (img.exists()){
                img.delete();
            }
            throw new IOException(e.getMessage());
        }
        return picture;
    }

    public void addUserSkin(UserSkin userSkin){
        pictureMapper.saveUserSkin(userSkin);
    }

    public List<Picture> getUserSkinList(Integer userId){
        List<UserSkin> userSkinList = pictureMapper.selectSkinListById(userId);
        List<Picture> pictures = new LinkedList<>();
        userSkinList.forEach(userSkin -> {
            pictures.add(pictureMapper.selectPictureById(userSkin.getPictureId()));
        });
        return pictures;
    }

    public void updateSkin(UserSkin userSkin){
        JwtUser curUser = (JwtUser) SecurityUtils.getUserDetails();
        if (!curUser.getUserId().equals(userSkin.getUserId())){
            throw new RuntimeException("无权限");
        }
        userMapper.updateSkin(userSkin);
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void deleteSkin(UserSkin userSkin){
        JwtUser curUser = (JwtUser) SecurityUtils.getUserDetails();
        if (!curUser.getUserId().equals(userSkin.getUserId())){
            throw new RuntimeException("无权限");
        }
        User user=userMapper.getUserByUserId(userSkin.getUserId());
        if (user.getUserSkinId().equals(userSkin.getPictureId())){
            throw new RuntimeException("皮肤正在使用");
        }
        pictureMapper.deleteUserSkin(userSkin);
        deletePic(userSkin.getPictureId());
    }
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void deletePic(Integer pictureId){
        Picture picture = pictureMapper.selectPictureById(pictureId);
        String url=picture.getUrl();
        String realName=url.substring(url.lastIndexOf('/')+1);
        File file = new File(picRoot,realName);
        pictureMapper.deletePicById(pictureId);
        if (file.exists()){
            file.delete();
        }
    }
}
