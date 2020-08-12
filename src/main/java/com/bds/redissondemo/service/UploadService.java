package com.bds.redissondemo.service;

import com.bds.redissondemo.utils.FileNameUtils;
import org.nutz.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * @Author :Kevin Ding;
 * @TIME :2020/8/12;
 * @TODO :图片上传;
 */
@Service
public class UploadService {
    @Resource
    private Dao mysqlDao;

    public int uploadImg(MultipartFile[] file, HttpServletRequest request){
        String localPath = "E:/BaiduNetdiskDownload/";
        int num = 0;
        for (MultipartFile multipartFile : file) {
            String fileName = multipartFile.getOriginalFilename();
            fileName = FileNameUtils.getFileName(fileName);
            String URL = "";

            if (FileNameUtils.upload(multipartFile,localPath,fileName)){
                num++;
            }
        }


        return num;
    }
}
