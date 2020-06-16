package com.hao.movieshareback.service.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hao.movieshareback.model.SystemMessage;
import com.hao.movieshareback.model.Video;
import com.hao.movieshareback.model.bo.VideoUploadMessage;
import com.hao.movieshareback.model.type.MessageType;
import com.hao.movieshareback.vo.auth.UserVo;
import org.springframework.stereotype.Component;

@Component
public class VideoUploadMessageConvert implements MessageConvert{

    public static class VideoUploadMessageParam{
        private Integer uploadUserId;
        private Integer videoId;
        private String userName;
        private String userAvatar;
        private String videoTitle;

        public VideoUploadMessageParam(Integer uploadUserId, Integer videoId, String userName, String userAvatar, String videoTitle) {
            this.uploadUserId = uploadUserId;
            this.videoId = videoId;
            this.userName = userName;
            this.userAvatar = userAvatar;
            this.videoTitle = videoTitle;
        }

        public Integer getUploadUserId() {
            return uploadUserId;
        }

        public void setUploadUserId(Integer uploadUserId) {
            this.uploadUserId = uploadUserId;
        }

        public Integer getVideoId() {
            return videoId;
        }

        public void setVideoId(Integer videoId) {
            this.videoId = videoId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserAvatar() {
            return userAvatar;
        }

        public void setUserAvatar(String userAvatar) {
            this.userAvatar = userAvatar;
        }

        public String getVideoTitle() {
            return videoTitle;
        }

        public void setVideoTitle(String videoTitle) {
            this.videoTitle = videoTitle;
        }
    }

    @Override
    public SystemMessage convertMessage(Object messageBo) {
        VideoUploadMessage videoUploadMessage = (VideoUploadMessage)messageBo;
        UserVo userVo = videoUploadMessage.getUploadUser();
        Video video = videoUploadMessage.getVideo();
        VideoUploadMessageParam videoUploadMessageParam =new VideoUploadMessageParam(userVo.getUserId(),video.getVideoId(),userVo.getUserName(),userVo.getAvatarUrl(),video.getVideoTitle());
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonParams = null;
        try {
            jsonParams=objectMapper.writeValueAsString(videoUploadMessageParam);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        SystemMessage systemMessage = new SystemMessage(MessageType.VIDEO_UPLOAD_MESSAGE.getMessageType(),jsonParams,"",videoUploadMessage.getFollowingUserId());
        return systemMessage;
    }

    @Override
    public Object boToPoMessage(SystemMessage poMessage) {
        return null;
    }

    @Override
    public Class<?> getRegistryClass() {
        return VideoUploadMessage.class;
    }
}
