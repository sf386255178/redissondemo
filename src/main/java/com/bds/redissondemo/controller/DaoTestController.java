package com.bds.redissondemo.controller;

import com.bds.redissondemo.service.DaoTestService;
import com.bds.redissondemo.utils.MacUtils;
import com.bds.redissondemo.utils.RedisLockUtils;
import org.nutz.dao.entity.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(HttpServletRequest request){
        //先获取到ip
        String ipAddress = request.getHeader("x-forwarded-for");
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress= inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15
            if(ipAddress.indexOf(",")>0){
                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
            }
        }
        String mac = null;
        try {
            mac = MacUtils.getMac(ipAddress);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return mac;
    }

    @RequestMapping("/getMac")
    @ResponseBody
    public String getMac(){
//        String ip = "192.168.2.65";
        String macAddress = "";
        return macAddress;
    }


    public static String getMac(String ipAddress) throws SocketException,
            UnknownHostException {
        String str = "";
        String macAddress = "";
        final String LOOPBACK_ADDRESS = "127.0.0.1";
        // 如果为127.0.0.1,则获取本地MAC地址。
        if (LOOPBACK_ADDRESS.equals(ipAddress)) {
            InetAddress inetAddress = InetAddress.getLocalHost();
            // 貌似此方法需要JDK1.6。
            byte[] mac = NetworkInterface.getByInetAddress(inetAddress).getHardwareAddress();
            // 下面代码是把mac地址拼装成String
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                if (i != 0) {
                    sb.append("-");
                }
                // mac[i] & 0xFF 是为了把byte转化为正整数
                String s = Integer.toHexString(mac[i] & 0xFF);
                sb.append(s.length() == 1 ? 0 + s : s);
            }
            // 把字符串所有小写字母改为大写成为正规的mac地址并返回
            macAddress = sb.toString().trim().toUpperCase();
            return macAddress;
        } else {
            // 获取非本地IP的MAC地址
            try {
                //System.out.println(ipAddress);
                Process p = Runtime.getRuntime().exec("nbtstat -A " + ipAddress);
                System.out.println("===process=="+p);
                InputStreamReader ir = new InputStreamReader(p.getInputStream());
                BufferedReader br = new BufferedReader(ir);

                while ((str = br.readLine()) != null) {
                    if(str.indexOf("MAC") > 1 ){
                        macAddress = str.substring(str.indexOf("MAC") + 9, str.length());
                        macAddress = macAddress.trim();
                        System.out.println("macAddress:" + macAddress);
                        break;
                    }
                }
                p.destroy();
                br.close();
                ir.close();
            } catch (IOException ex) {
            }
            return macAddress;
        }
    }





}
