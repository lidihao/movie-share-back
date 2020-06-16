package com.hao.movieshareback.service.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hao.movieshareback.model.SystemMessage;
import com.hao.movieshareback.model.Video;
import com.hao.movieshareback.model.bo.VideoCommentMessage;
import com.hao.movieshareback.model.type.MessageType;
import com.hao.movieshareback.vo.auth.UserVo;
import org.springframework.stereotype.Service;

@Service
public class VideoCommentMessageConvert implements MessageConvert{

    public static class VideoCommentMessageParams{
        private Integer userId;
        private Integer videoId;
        private String userName;
        private String userAvatar;
        private String videoTitle;

        public VideoCommentMessageParams(Integer userId, Integer videoId, String userName, String userAvatar, String videoTitle) {
            this.userId = userId;
            this.videoId = videoId;
            this.userName = userName;
            this.userAvatar = userAvatar;
            this.videoTitle = videoTitle;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
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
        VideoCommentMessage videoCommentMessage = (VideoCommentMessage)messageBo;
        UserVo commentUser=videoCommentMessage.getCommentUserVo();
        Video video = videoCommentMessage.getVideo();
        VideoCommentMessageParams videoCommentMessageParams = new VideoCommentMessageParams(
                commentUser.getUserId(),video.getVideoId(),commentUser.getUserName(),commentUser.getAvatarUrl(),video.getVideoTitle());
        ObjectMapper objectMapper = new ObjectMapper();
        String videoCommentParamsJson=null;
        try {
            videoCommentParamsJson=objectMapper.writeValueAsString(videoCommentMessageParams);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        SystemMessage systemMessage = new SystemMessage(MessageType.COMMENT_MESSAGE.getMessageType(),videoCommentParamsJson,videoCommentMessage.getCommentContent(),
                videoCommentMessage.getVideo().getUploadUserId());
        return systemMessage;
    }

    @Override
    public Object boToPoMessage(SystemMessage poMessage) {
        return null;
    }

    @Override
    public Class<?> getRegistryClass() {
        return VideoCommentMessage.class;
    }
}
