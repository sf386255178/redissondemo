package com.bds.redissondemo.utils;

import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author :Kevin Ding;
 * @TIME :2020/6/16;
 * @TODO :redisdon加锁工具类;
 */
@Component
public class RedisLockUtils {

    @Autowired
    private RedissonClient redisson;

    public  void lock(String key,String num){
        RLock lock = redisson.getLock(key);
        boolean locked = false;

        try {
            locked = lock.tryLock(10, TimeUnit.SECONDS);
            if (locked){
                //开始业务处理
                System.out.println(num + "已加锁###");
                long time = System.currentTimeMillis();
                Thread.sleep(100);
                long endTime = System.currentTimeMillis();
                System.out.println(num + "模拟业务耗时结束 " + (endTime - time) +"ms");
            }else {
                System.out.println(num + "未加锁");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if (locked){
                System.out.println(num + "释放锁");
                lock.unlock();
            }
        }

    }

    public void testLock(){
        for (int i = 0; i < 100; i++) {
            new Thread(){
                @Override
                public void run() {
                    lock("test",this.getName());
                }
            }.start();
        }
    }

    /**
     * 验证锁存在
     * @return
     */
    public boolean getKey(){
        boolean isLocked = false;
        String key = "test";
        RLock testLock = redisson.getLock(key);
        boolean locked = false;
        try {
            locked = testLock.tryLock(10, TimeUnit.SECONDS);
            isLocked = locked;
            if (locked){
                System.out.println("释放锁");
                Thread.sleep(1000*5);
                testLock.unlock();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        finally {
//            if (locked){
//                System.out.println("释放锁");
//                testLock.unlock();
//            }
//        }

        return isLocked;


    }



    /**
     * 写锁
     */
    public void writeLock(){
        RReadWriteLock readWriteLock = redisson.getReadWriteLock("read_write_lock");
        RLock writeLock = readWriteLock.writeLock();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++){
            executorService.submit(()->{
                try{
                    writeLock.lock();
                    System.out.println("线程 "+Thread.currentThread().getId()+" 获得锁："+System.currentTimeMillis());
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    System.out.println("线程 "+Thread.currentThread().getId()+" 释放锁："+System.currentTimeMillis());
                    writeLock.unlock();
                }
            });
        }
    }



}
