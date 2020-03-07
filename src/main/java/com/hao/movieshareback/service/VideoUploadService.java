package com.hao.movieshareback.service;

import cn.hutool.core.util.IdUtil;
import com.hao.movieshareback.dao.PictureMapper;
import com.hao.movieshareback.dao.UserMapper;
import com.hao.movieshareback.dao.VideoApprovalMapper;
import com.hao.movieshareback.dao.VideoFileMapper;
import com.hao.movieshareback.model.*;
import com.hao.movieshareback.model.type.ApprovalType;
import com.hao.movieshareback.utils.FileTypeUtils;
import com.hao.movieshareback.utils.FileUtil;
import com.hao.movieshareback.utils.SecurityUtils;
import com.hao.movieshareback.utils.VideoUtils;
import com.hao.movieshareback.vo.ScreenPicture;
import com.hao.movieshareback.vo.VideoFileRawInfo;
import com.hao.movieshareback.vo.VideoMergeFileVo;
import com.hao.movieshareback.vo.VideoMeta;
import com.hao.movieshareback.vo.auth.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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

    @Value("${pic.upload.root}")
    private String imgRoot;

    @Value("${pic.upload.url.root}")
    private String imgUrlRoot;

    @Autowired
    private PictureMapper pictureMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private VideoFileMapper videoFileMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VideoApprovalMapper videoApprovalMapper;

    @Autowired
    private TagService tagService;

    public void receiveFileChunk(Chunk chunk){
        MultipartFile multipartFile = chunk.getFile();
        File tmpDir = new File(tmpFileUrl);
        File tempFile = FileUtil.toFile(multipartFile,tmpDir);
        redisTemplate.opsForHash().put(chunk.getIdentifier(),chunk.getChunkNumber(),tempFile.getName());
        redisTemplate.expire(chunk.getIdentifier(),keyExpire, TimeUnit.HOURS);
    }

    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public VideoFile mergeFileChunk(VideoMergeFileVo videoMergeFileVo) throws Exception {
        Map<Integer,String> sortedMap = new TreeMap<>();
        Cursor<Map.Entry> cursor= redisTemplate.opsForHash().scan(videoMergeFileVo.getUniqueIdentifier(), ScanOptions.NONE);
        cursor.forEachRemaining(entry -> {
            sortedMap.put((Integer) entry.getKey(),(String)entry.getValue());
        });
        redisTemplate.delete(videoMergeFileVo.getUniqueIdentifier());
        //合并文件
        List<File> sourceFile = new ArrayList<>(sortedMap.size());
        for (Integer chunkNum:sortedMap.keySet()){
            sourceFile.add(new File(tmpFileUrl,sortedMap.get(chunkNum)));
        }
        String uuid = IdUtil.simpleUUID();
        String suffix = FileUtil.getExtensionName(videoMergeFileVo.getFileName());
        File target = File.createTempFile(uuid,suffix,new File(root));
        FileUtil.mergeFiles(sourceFile,target);
        for (File file:sourceFile){
            file.delete();
        }

        String uploaderName = SecurityUtils.getUsername();
        User user=userMapper.getUserByUserName(uploaderName);
        String fileUrl=urlRoot+target.getName();
        //poster_picture
        VideoFileRawInfo rawInfo = VideoUtils.getScreenshot(target.getAbsolutePath());

        ScreenPicture screenPicture=rawInfo.getScreenPicture();
        String imgPath = screenPicture.getPath();
        File posterSrc = new File(imgPath);
        File posterDes = new File(imgRoot,IdUtil.simpleUUID()+"."+VideoUtils.SCREEN_SHOT_FORMAT);
        FileUtil.copy(posterSrc,posterDes,false);
        posterSrc.delete();
        Picture picture = new Picture(posterDes.getName(),screenPicture.getHeight(),screenPicture.getSize(),
                screenPicture.getWidth(),imgUrlRoot+posterDes.getName());
        pictureMapper.save(picture);
        VideoFile videoFile = new VideoFile(videoMergeFileVo.getFileName(), videoMergeFileVo.getSize(),user.getUserId(),
                ApprovalType.PROCESSING.getTag(),fileUrl, FileTypeUtils.getFileType(suffix),picture.getPictureId());
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

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void addVideoApproval(VideoMeta videoMeta){
        JwtUser jwtUser = (JwtUser) SecurityUtils.getUserDetails();
        VideoApproval videoApproval = new VideoApproval(jwtUser.getUserId()
                ,videoMeta.getPictureId(),videoMeta.getTitle(),
                videoMeta.getIntroduce(),videoMeta.getCategoryId(),ApprovalType.PROCESSING.getTag());
        //
        BaseModel.setNewCreate(videoApproval,jwtUser.getUsername(),new Date());
        BaseModel.setUpdated(videoApproval,jwtUser.getUsername(),new Date());
        videoApprovalMapper.save(videoApproval);
        if (videoMeta.getTagList()!=null) {
            tagService.relatedTagAndApproval(videoMeta.getTagList(), videoApproval.getVideoApprovalId());
        }
        int index=0;
        for (Integer videoFileId:videoMeta.getVideoFileId()){
            videoFileMapper.relateVideoApproval(videoFileId,videoApproval.getVideoApprovalId(),index++);
        }
    }
}
