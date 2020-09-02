package com.bds.redissondemo.config;

import com.bds.redissondemo.utils.Const;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * @Author :Kevin Ding;
 * @TIME :2020/9/2;
 * @TODO :随便自定义的配置类，随项目启动;
 */
@Configuration
@DependsOn("getRedisson")//redisson启动后再加载此类
public class DiyConfig {

    /*@Bean(value = "",name = "")
    value： name属性的别名，在不需要其他属性时使用，也就是说value 就是默认值
    name： 此bean 的名称，或多个名称，主要的bean的名称加别名。如果未指定，则
    bean的名称是带注解方法的名称。如果指定了，方法的名称就会忽略，如果没有其他
    属性声明的话，bean的名称和别名可能通过value属性配置*/
    @Bean//未指定bean 的名称，默认采用的是 "方法名" + "首字母小写"的配置方式
    public void startDiy(){
        System.out.println("！！！！自定义配置类已启动" + Thread.currentThread().getName());
        Const.thread.start();
    }
}
