package com.bds.redissondemo.controller;

import com.bds.redissondemo.service.UploadService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author :Kevin Ding;
 * @TIME :2020/8/12;
 * @TODO :;
 */
@Controller
public class UploadController {
    @Autowired
    private UploadService service;

    /**
     * 上传图片到本地E盘，并生成可访问的url路径
     * @param file
     * @param request
     * @return 已上传图片的url
     */

    @ApiOperation(value = "上传图片并返回访问地址",notes = "上传图片并返回访问地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file",value = "上传的图片，可以多张图片",required = true,dataType = "MultipartFile[]"),
            @ApiImplicitParam(name = "request",value = "请求体",required = true,dataType = "HttpServletRequest")
    })
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public List upload(MultipartFile[] file, HttpServletRequest request){
        List num = service.uploadImg(file,request);
        return  num ;
    }

}
