package com.bds.redissondemo.test;

import java.util.concurrent.Callable;

/**
 * @Author :Kevin Ding;
 * @TIME :2020/8/28;
 * @TODO :;
 */
public class Test {
    public static void main(String[] args) throws Exception {
        int[] a = {0};
        Runnable run = new Runnable() {
            @Override
            public void run() {
                a[0]++;
            }
        };
        run.run();

        Callable callable = new Callable() {
            @Override
            public Object call() throws Exception {
                return a[0]++;
            }
        };


        Object num = callable.call();
        System.out.println("回调" + num);
        for (int i = 0; i < a.length ; i++) {
            System.out.println(a[i]);
        }
    }
}
