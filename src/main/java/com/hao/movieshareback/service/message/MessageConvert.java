package com.hao.movieshareback.service.message;

import com.hao.movieshareback.model.SystemMessage;

public interface MessageConvert {
    SystemMessage convertMessage(Object messageBo);
    Object boToPoMessage(SystemMessage poMessage);
    Class<?> getRegistryClass();
}
