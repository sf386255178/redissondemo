package com.bds.redissondemo.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @Author :Kevin Ding;
 * @TIME :2020/9/11;
 * @TODO :测试;
 */
@Controller
public class HelloController {

    //nacos配置动态刷新的值
    @NacosValue(value = "${nacos.testValue:hello nacos!}",autoRefreshed = true)
    private String testValue;

    @RequestMapping("/toHello")
    public String hello(){
        return "hello";
    }

    @RequestMapping("/nacos")
    @ResponseBody
    public String nacosTest(){
      return testValue;
    }


}
