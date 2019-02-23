package com.example.demo.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置虚拟文件路径的映射
 * @author ggg1235
 */
@Configuration
public class MyFileConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/ueditor/**").addResourceLocations("file:/Users/guxukai/sketch/Spring/NewsSystem2.0/src/main/resources/static/ueditor/");
    }
}
