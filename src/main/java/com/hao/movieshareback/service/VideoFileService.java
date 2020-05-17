package com.hao.movieshareback.service;

import com.hao.movieshareback.dao.VideoFileMapper;
import com.hao.movieshareback.model.VideoFile;
import com.mchange.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
@Service
public class VideoFileService {
    @Autowired
    private VideoFileMapper videoFileMapper;
    @Value("${video.upload.root}")
    private String root;
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteVideoFile(Integer videoFileId){
        VideoFile videoFile = videoFileMapper.getVideoFileDetail(videoFileId);
        if (videoFile==null){
            return;
        }
        videoFileMapper.deleteByVideoFileId(videoFileId);
        String url = videoFile.getFileUrl();
        String fileName=url.substring(url.lastIndexOf("/")+1);
        File file = new File(root,fileName);
        if (file.exists()){
            file.delete();
        }
    }
}
