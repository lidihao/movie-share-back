package com.hao.movieshareback.service;

import cn.hutool.core.util.IdUtil;
import com.hao.movieshareback.dao.UserMapper;
import com.hao.movieshareback.dao.VideoFileMapper;
import com.hao.movieshareback.exception.MergeFileException;
import com.hao.movieshareback.model.Chunk;
import com.hao.movieshareback.model.User;
import com.hao.movieshareback.model.VideoFile;
import com.hao.movieshareback.model.type.ApprovalType;
import com.hao.movieshareback.utils.FileTypeUtils;
import com.hao.movieshareback.utils.FileUtil;
import com.hao.movieshareback.utils.SecurityUtils;
import com.hao.movieshareback.vo.VideoFileVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;


@Service
public class VideoUploadService {
    @Value("${video.upload.tmp.dir}")
    private String tmpFileUrl;

    @Value("${video.upload.expire}")
    private Integer keyExpire;

    @Value("${video.upload.root}")
    private String root;

    @Value("${video.upload.url.root}")
    private String urlRoot;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private VideoFileMapper videoFileMapper;

    @Autowired
    private UserMapper userMapper;

    public void receiveFileChunk(Chunk chunk){
        MultipartFile multipartFile = chunk.getFile();
        File tmpDir = new File(tmpFileUrl);
        File tempFile = FileUtil.toFile(multipartFile,tmpDir);
        redisTemplate.opsForHash().put(chunk.getIdentifier(),chunk.getChunkNumber(),tempFile.getName());
        redisTemplate.expire(chunk.getIdentifier(),keyExpire, TimeUnit.HOURS);
    }

    public VideoFile mergeFileChunk(VideoFileVo videoFileVo) throws IOException {
        Map<Integer,String> sortedMap = new TreeMap<>();
        Cursor<Map.Entry> cursor= redisTemplate.opsForHash().scan(videoFileVo.getUniqueIdentifier(), ScanOptions.NONE);
        cursor.forEachRemaining(entry -> {
            sortedMap.put((Integer) entry.getKey(),(String)entry.getValue());
        });
        redisTemplate.delete(videoFileVo.getUniqueIdentifier());
        //合并文件
        List<File> sourceFile = new ArrayList<>(sortedMap.size());
        for (Integer chunkNum:sortedMap.keySet()){
            sourceFile.add(new File(tmpFileUrl,sortedMap.get(chunkNum)));
        }
        String uuid = IdUtil.simpleUUID();
        String suffix = FileUtil.getExtensionName(videoFileVo.getFileName());
        File target = File.createTempFile(uuid,suffix,new File(root));
        FileUtil.mergeFiles(sourceFile,target);
        for (File file:sourceFile){
            file.delete();
        }

        String uploaderName = SecurityUtils.getUsername();
        User user=userMapper.getUserByUserName(uploaderName);
        String fileUrl=urlRoot+target.getName();
        VideoFile videoFile = new VideoFile(videoFileVo.getFileName(),videoFileVo.getSize(),user.getUserId(),
                ApprovalType.PROCESSING.getTag(),fileUrl, FileTypeUtils.getFileType(suffix));
        videoFileMapper.save(videoFile);
        return videoFile;
    }

    public Set<Integer> checkFileChunk(Chunk chunk){
        Set<Integer> hasSendSet = new HashSet<>();
        String identify = chunk.getIdentifier();
        Cursor<Map.Entry> cursor= redisTemplate.opsForHash().scan(identify, ScanOptions.NONE);
        cursor.forEachRemaining(entry -> {
            hasSendSet.add((Integer) entry.getKey());
        });
        return hasSendSet;
    }
}
