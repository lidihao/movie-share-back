package com.hao.movieshareback.controller;

import com.hao.movieshareback.model.Chunk;
import com.hao.movieshareback.model.VideoFile;
import com.hao.movieshareback.service.VideoUploadService;
import com.hao.movieshareback.vo.ResultBody;
import com.hao.movieshareback.vo.VideoMergeFileVo;
import com.hao.movieshareback.vo.VideoMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@RestController
@RequestMapping("/videoUpload")
public class VideoUploadController {

    @Value("${video.upload.tmp.dir}")
    private String tmpFileUrl;

    @Autowired
    private VideoUploadService videoUploadService;

    @PostMapping("/uploadChunk")
    public ResultBody uploadVideoChunk(Chunk chunk){
        videoUploadService.receiveFileChunk(chunk);
        return ResultBody.success();
    }

    @GetMapping("/checkUploadChunk")
    public ResultBody testVideoChunk(Chunk chunk, HttpServletResponse response){
        Set<Integer> hasSendFile = videoUploadService.checkFileChunk(chunk);
        if (!hasSendFile.contains(chunk.getChunkNumber())){
            response.setStatus(300);
        }
        return ResultBody.success(hasSendFile);
    }

    @PostMapping("/mergeFile")
    public ResultBody mergeTempFile(@RequestBody VideoMergeFileVo videoMergeFileVo){
        try {
            VideoFile videoFile=videoUploadService.mergeFileChunk(videoMergeFileVo);
            return ResultBody.success(videoFile);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultBody.error("融合文件出错");
        }
    }

    @PostMapping("/videoMeta")
    public ResultBody addVideoMeta(@RequestBody VideoMeta videoMeta){
        videoUploadService.addVideoApproval(videoMeta);
        return ResultBody.success("上传成功");
    }
}
