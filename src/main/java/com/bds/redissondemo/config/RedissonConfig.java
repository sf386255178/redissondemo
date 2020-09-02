package com.bds.redissondemo.config;

import com.bds.redissondemo.utils.Const;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author :Kevin Ding;
 * @TIME :2020/6/16;
 * @TODO :redisson配置类生成bean;
 */
@Configuration
public class RedissonConfig {
    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    @Bean
    public RedissonClient getRedisson(){
        Const.thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("====自定义配置类已赋值");
            }
        });
        Config config = new Config();
        config.useSingleServer().setAddress("redis://" + host + ":" + port);
        return Redisson.create(config);
    }

}
