package com.bds.redissondemo.controller;

import com.bds.redissondemo.service.DaoTestService;
import com.bds.redissondemo.utils.RedisLockUtils;
import org.nutz.dao.entity.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author :Kevin Ding;
 * @TIME :2020/6/16;
 * @TODO :;
 */
@Controller
public class DaoTestController {
    @Autowired
    DaoTestService service;

    @Autowired
    RedisLockUtils redisLockUtils;

    @RequestMapping("/test")
    @ResponseBody
    public List<Record> getData(){
        return service.getData();
    }

    @RequestMapping("/lockTest")
    @ResponseBody
    public String lockTest(){
        redisLockUtils.testLock();
        return "测试";
    }

    @RequestMapping("/getLock")
    @ResponseBody
    public String getLock(){
       boolean lock = redisLockUtils.getKey();
       if (lock){
           return "获取到锁" + lock;
       }else {
           return "未获取到锁" + lock;
       }
    }


    @RequestMapping("/update")
    @ResponseBody
    public String update(){
        int num = 0;
        for (int i = 0; i < 10 ; i++) {
            num = service.updateTest(i);
            try {
                Thread.sleep(1000*3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (num > 0){
            return  "修改成功";
        }else {
            return "error";
        }
    }


    @RequestMapping("/writeLock")
    @ResponseBody
    public String writeLock(){
        for (int i = 0; i < 10; i++) {
            service.updateBatch(i);
        }
        return "success";
    }



}
