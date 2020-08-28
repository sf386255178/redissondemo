package com.bds.redissondemo.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Author :Kevin Ding;
 * @TIME :2020/8/28;
 * @TODO :;
 */
@Service
public class AsyncTestService {

    @Async//异步方法
    public String task1() {
        long time1 = System.currentTimeMillis();
        try {
            Thread.sleep(1000l * 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long time2 = System.currentTimeMillis();
        String time = "task1耗时:" + (time2 - time1) + "ms";
        return  time;
    }

    @Async
    public String task2() {
        long time1 = System.currentTimeMillis();
        try {
            Thread.sleep(1000l * 2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long time2 = System.currentTimeMillis();
        String time = "task2耗时:" + (time2 - time1) + "ms";
        return  time;
    }

    @Async
    public String task3() {
        long time1 = System.currentTimeMillis();
        try {
            Thread.sleep(1000l * 3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long time2 = System.currentTimeMillis();
        String time = "task3耗时:" + (time2 - time1) + "ms";
        return  time;
    }
}
