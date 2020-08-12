package com.bds.redissondemo.utils;

import java.util.UUID;

/**
 * @Author :Kevin Ding;
 * @TIME :2020/8/12;
 * @TODO :;
 */
public class UUIDUtils {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
