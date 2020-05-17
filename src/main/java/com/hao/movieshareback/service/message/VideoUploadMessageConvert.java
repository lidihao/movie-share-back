package com.hao.movieshareback.service.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hao.movieshareback.model.SystemMessage;
import com.hao.movieshareback.model.bo.VideoUploadMessage;
import com.hao.movieshareback.model.type.MessageType;
import org.springframework.stereotype.Component;

@Component
public class VideoUploadMessageConvert implements MessageConvert{

    public static class VideoUploadMessageParam{
        private Integer uploadUserId;
        private Integer videoId;

        public VideoUploadMessageParam(Integer uploadUserId, Integer videoId) {
            this.uploadUserId = uploadUserId;
            this.videoId = videoId;
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
    }

    @Override
    public SystemMessage convertMessage(Object messageBo) {
        VideoUploadMessage videoUploadMessage = (VideoUploadMessage)messageBo;
        VideoUploadMessageParam videoUploadMessageParam =new VideoUploadMessageParam(videoUploadMessage.getUploadUserId(),videoUploadMessage.getVideoId());
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
