package com.hao.movieshareback.service.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hao.movieshareback.model.SystemMessage;
import com.hao.movieshareback.model.bo.VideoApplyMessage;
import com.hao.movieshareback.model.type.ApprovalType;
import com.hao.movieshareback.model.type.MessageType;
import org.springframework.stereotype.Component;

@Component
public class VideoApplyMessageConvert implements MessageConvert{

    public static class VideoApplyMessageParam{
        private Integer videoApprovalId;
        private ApprovalType approvalType;

        public VideoApplyMessageParam() {
        }

        public Integer getVideoApprovalId() {
            return videoApprovalId;
        }

        public void setVideoApprovalId(Integer videoApprovalId) {
            this.videoApprovalId = videoApprovalId;
        }

        public ApprovalType getApprovalType() {
            return approvalType;
        }

        public void setApprovalType(ApprovalType approvalType) {
            this.approvalType = approvalType;
        }
    }


    @Override
    public SystemMessage convertMessage(Object messageBo) {
        VideoApplyMessage videoApplyMessage = (VideoApplyMessage)messageBo;
        VideoApplyMessageParam videoApplyMessageParam = new VideoApplyMessageParam();
        String systemContent=null;
        videoApplyMessageParam.setApprovalType(videoApplyMessage.getApprovalType());
        videoApplyMessageParam.setVideoApprovalId(videoApplyMessage.getVideoApproval().getVideoApprovalId());
        if (videoApplyMessage.getApprovalType().equals(ApprovalType.PASS)){
            systemContent = "通过审批";
        }
        if (videoApplyMessage.getApprovalType().equals(ApprovalType.REJECT)){
            systemContent ="被驳回";
        }

        String jsonParams=null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            jsonParams = objectMapper.writeValueAsString(videoApplyMessageParam);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        SystemMessage systemMessage = new SystemMessage(MessageType.VIDEO_APPROVAL_MESSAGE.getMessageType(),
                jsonParams,systemContent,videoApplyMessage.getVideoApproval().getUploadUserId());
        return systemMessage;
    }

    @Override
    public Object boToPoMessage(SystemMessage poMessage) {
        return null;
    }

    @Override
    public Class<?> getRegistryClass() {
        return VideoApplyMessage.class;
    }
}
