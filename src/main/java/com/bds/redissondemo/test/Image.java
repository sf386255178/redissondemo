package com.bds.redissondemo.test;

import com.baidu.aip.imageclassify.AipImageClassify;
import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * @Author :Kevin Ding;
 * @TIME :2021/4/8;
 * @TODO :百度AI 识别图片;
 */
public class Image {
    //设置APPID/AK/SK   登录百度控制台获取https://login.bce.baidu.com/
    public static final String APP_ID = "23952105";
    public static final String API_KEY = "3Ewufcjbcfigjw5sgbVr6QXY";
    public static final String SECRET_KEY = "WfV5aZWwFi5qiD0MuPC2s3P9ixy7AB8X";

    public static void main(String[] args) {
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
//        System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        // 调用接口
        String path = "C:\\Users\\Administrator\\Desktop\\4d7e72eb49d9e6af3015551e3f89f0b.png";
        JSONObject res = client.basicGeneral(path, new HashMap<String, String>());
        System.out.println(res.toString(2));

    }

}
