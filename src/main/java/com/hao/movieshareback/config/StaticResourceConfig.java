package com.hao.movieshareback.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticResourceConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //将图片的访问路径映射
        registry.addResourceHandler("/image/**").addResourceLocations("file:/home/lidihao/upload/image/");
        registry.addResourceHandler("/video/**").addResourceLocations("file:/home/lidihao/upload/video/root/");
    }
}
