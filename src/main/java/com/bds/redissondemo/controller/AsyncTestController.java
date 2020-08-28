package com.bds.redissondemo.controller;

import com.bds.redissondemo.service.AsyncTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author :Kevin Ding;
 * @TIME :2020/8/28;
 * @TODO :;
 */
@Controller
public class AsyncTestController {

    @Autowired
    AsyncTestService service;

    @RequestMapping(value = "/aysnc")
    @ResponseBody
    public String doTask(){
        long time1 = System.currentTimeMillis();
        String t1 = service.task1();
        String t2 = service.task2();
        String t3 = service.task3();
        long time2 = System.currentTimeMillis();

        System.out.println("任务总耗时 ==" + (time2 - time1) + "ms");
        return  t1 + "|" +  t2 + "|" + t3;
    }
}
