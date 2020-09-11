package com.bds.redissondemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author :Kevin Ding;
 * @TIME :2020/8/5;
 * @TODO :注册自定义拦截器;
 */
@Component
public class InterceptirConfig implements WebMvcConfigurer {

//    @Autowired
//    private AuthenticationInterceptor interceptor;

    /**
     * token校验
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry  registry) {
        registry.addInterceptor(authenticationInterceptor())//添加自己的拦截器
                .addPathPatterns("/**")//需要拦截的请求
                .excludePathPatterns("/toHello");//放行的请求
    }

    /**
     * 静态资源
     * 因为WebMvcConfigurationSupport不走自动化的配置文件，所以一些静态文件，视图配置需要自己手动再添加一下
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/");
    }

    @Bean
    public AuthenticationInterceptor authenticationInterceptor(){
        return new AuthenticationInterceptor();// 自己写的拦截器
    }
}
