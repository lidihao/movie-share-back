package com.hao.movieshareback.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticResourceConfig implements WebMvcConfigurer {
    @Value("${pic.upload.root}")
    private String picRoot;
    @Value("${video.upload.root}")
    private String videoRoot;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //将图片的访问路径映射
        registry.addResourceHandler("/image/**").addResourceLocations("file:"+picRoot+"/");
        registry.addResourceHandler("/video/**").addResourceLocations("file:"+videoRoot+"/");
    }
}
