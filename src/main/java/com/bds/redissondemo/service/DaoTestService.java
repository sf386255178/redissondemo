package com.bds.redissondemo.service;

import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.entity.Record;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author :Kevin Ding;
 * @TIME :2020/6/16;
 * @TODO :数据测试;
 */
@Service
public class DaoTestService {
    @Resource
    private Dao  mysqlDao;

    @Autowired
    private RedissonClient redisson;

    public List<Record> getData(){
        List<Record> data = mysqlDao.query("t_shop_detail", Cnd.where("id","<",10));
        return  data;
    }

    /**
     * 多线程修改测试
     * @param num
     * @return
     */
    public  String update(int num){
        String bds_id = "1581574147411968";
        RReadWriteLock readWriteLock = redisson.getReadWriteLock(bds_id);
        RLock writeLock = readWriteLock.writeLock();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++){
            Callable<String> task = new Callable<String>() {
                @Override
                public String call() throws Exception {
                    try{
                        writeLock.lock();
                        System.out.println("线程 "+Thread.currentThread().getId()+" 获得锁："+System.currentTimeMillis());
                        mysqlDao.update("t_shop_detail", Chain.make("ele_base_price", num), Cnd.where("id", "=", 1));
                        Thread.sleep(1000*2);
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        System.out.println("线程 "+Thread.currentThread().getId()+" 释放锁："+System.currentTimeMillis());
                        writeLock.unlock();
                    }
                    return "";
                }
            };
            executorService.submit(task);
        }
        executorService.shutdown();

        return "";
    }


    /**
     * 写锁设置监控锁的看门狗
     * @param price
     * @return
     */
    public  int updateTest(int price){
        int num = 0;
        String bds_id = "1581574151606272";
        RReadWriteLock lock = redisson.getReadWriteLock(bds_id);
        boolean locked = false;
        try {
            // 尝试加锁，最多等待100s,上锁以后10秒自动解锁
            locked = lock.writeLock().tryLock(100,10, TimeUnit.SECONDS);
            if (locked){
                //开始业务处理
                long time = System.currentTimeMillis();
                System.out.println(price + "已加锁###");
                num = mysqlDao.update("t_shop_detail", Chain.make("ele_base_price",price),Cnd.where("bds_sid","=","1581574151606272"));
                long endTime = System.currentTimeMillis();
                System.out.println(price + "模拟业务耗时结束 " + (endTime - time) +"ms");
            }else {
                System.out.println(price + "未加锁");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if (locked){
                System.out.println(price + "释放锁");
//                lock.writeLock().unlock();
            }
        }
        return num;
    }


    /**
     * 单个数据修改
     * @param price
     * @return
     */
    public  int updateOne(int price){
        int num = 0;
         num = mysqlDao.update("t_shop_detail", Chain.make("ele_base_price",price),Cnd.where("id","=",1));
        return num;
    }


    /**
     * 写锁批修改
     * @param num
     * @return
     */
    public  String updateBatch(int num){
        String bds_id = "1581574147411968";
        RReadWriteLock readWriteLock = redisson.getReadWriteLock(bds_id);
        RLock writeLock = readWriteLock.writeLock();
        try {
            // 尝试加锁，上锁以后10秒自动解锁
            writeLock.lock(10,TimeUnit.SECONDS);
            System.out.println("线程 " + Thread.currentThread().getId() + " 获得锁：" + System.currentTimeMillis());
            mysqlDao.update("t_shop_detail", Chain.make("ele_base_price", num), Cnd.where("id", "=", 1));
            Thread.sleep(1000 * 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        finally {
//            System.out.println("线程 " + Thread.currentThread().getId() + " 释放锁：" + System.currentTimeMillis());
//            writeLock.unlock();
//        }

        return "";
    }




}
