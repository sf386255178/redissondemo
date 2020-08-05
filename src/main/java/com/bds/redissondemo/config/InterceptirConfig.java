package com.bds.redissondemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
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

    @Override
    public void addInterceptors(InterceptorRegistry  registry) {
        registry.addInterceptor(authenticationInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public AuthenticationInterceptor authenticationInterceptor(){
        return new AuthenticationInterceptor();// 自己写的拦截器
    }
}
