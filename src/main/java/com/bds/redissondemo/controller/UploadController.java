package com.bds.redissondemo.controller;

import com.bds.redissondemo.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author :Kevin Ding;
 * @TIME :2020/8/12;
 * @TODO :;
 */
@Controller
public class UploadController {
    @Autowired
    private UploadService service;

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public String upload(MultipartFile[] file, HttpServletRequest request){
        int num = service.uploadImg(file,request);
        return "上传了" + num + "张图片";
    }

}
