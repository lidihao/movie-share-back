package com.hao.movieshareback.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.socket.aio.IoAction;
import com.hao.movieshareback.dao.PictureMapper;
import com.hao.movieshareback.model.Picture;
import com.hao.movieshareback.utils.FileUtil;
import com.hao.movieshareback.vo.PictureVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class PictureUploadService {

    @Value("${pic.upload.root}")
    private String picRoot;

    @Value("${pic.upload.url.root}")
    private String urlRoot;

    @Autowired
    private PictureMapper pictureMapper;

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
}
