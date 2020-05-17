package com.hao.movieshareback.service.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hao.movieshareback.model.SystemMessage;
import com.hao.movieshareback.model.bo.RateCommentMessage;
import com.hao.movieshareback.model.type.MessageType;
import org.springframework.stereotype.Component;

@Component
public class RateCommentMessageConvert implements MessageConvert{
    public static class RateCommentMessageParam{
        private Integer commentUserId;
        private Integer videoId;
        private Double rate;

        public RateCommentMessageParam(Integer commentUserId, Integer videoId, Double rate) {
            this.commentUserId = commentUserId;
            this.videoId = videoId;
            this.rate = rate;
        }

        public Integer getCommentUserId() {
            return commentUserId;
        }

        public void setCommentUserId(Integer commentUserId) {
            this.commentUserId = commentUserId;
        }

        public Integer getVideoId() {
            return videoId;
        }

        public void setVideoId(Integer videoId) {
            this.videoId = videoId;
        }

        public Double getRate() {
            return rate;
        }

        public void setRate(Double rate) {
            this.rate = rate;
        }
    }


    @Override
    public SystemMessage convertMessage(Object messageBo) {
        RateCommentMessage rateCommentMessage = (RateCommentMessage)messageBo;
        RateCommentMessageParam rateCommentMessageParam = new RateCommentMessageParam(rateCommentMessage.getUserId(),
                rateCommentMessage.getVideo().getVideoId(),rateCommentMessage.getRate());
        String jsonParams=null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            jsonParams=objectMapper.writeValueAsString(rateCommentMessageParam);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        SystemMessage systemMessage = new SystemMessage(MessageType.RATE_COMMENT_MESSAGE.getMessageType(),
                jsonParams,rateCommentMessage.getContent(),rateCommentMessage.getVideo().getUploadUserId());
        return systemMessage;
    }

    @Override
    public Object boToPoMessage(SystemMessage poMessage) {
        return null;
    }

    @Override
    public Class<?> getRegistryClass() {
        return RateCommentMessage.class;
    }
}
