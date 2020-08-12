package com.bds.redissondemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author :Kevin Ding;
 * @TIME :2020/8/12;
 * @TODO :配置资源映射路径，访问图片本地路径并映射成url;
 */
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * 资源映射路径
         * addResourceHandler：访问映射路径
         * addResourceLocations：资源绝对路径
         */
        registry.addResourceHandler("/BaiduNetdiskDownload/**")
                .addResourceLocations("file:E:/BaiduNetdiskDownload/");//linux环境file去掉，直接改成 "/home/...."
    }
}
