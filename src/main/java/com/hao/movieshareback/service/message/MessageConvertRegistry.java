package com.hao.movieshareback.service.message;

import com.hao.movieshareback.service.message.MessageConvert;
import com.hao.movieshareback.utils.SpringContextJobUtil;
import com.hao.movieshareback.utils.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@Component
public class MessageConvertRegistry  implements ApplicationContextAware {

    private Map<Class<?>, MessageConvert> customMessageConvert;
    private ApplicationContext applicationContext;

    @PostConstruct
    public void init(){
        Map<String,MessageConvert> allMessageConvert = applicationContext.getBeansOfType(MessageConvert.class);
        customMessageConvert=new HashMap<>(allMessageConvert.size());
        for (String key:allMessageConvert.keySet()){
            MessageConvert messageConvert = allMessageConvert.get(key);
            customMessageConvert.put(messageConvert.getRegistryClass(),messageConvert);
        }
    }

    public MessageConvert getMessageConvert(Class<?> clazz){
        return customMessageConvert.get(clazz);
    }

    @Override
    public void setApplicationContext( ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
}
