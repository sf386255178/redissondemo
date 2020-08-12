package com.bds.redissondemo.service;

import com.bds.redissondemo.utils.FileNameUtils;
import org.nutz.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author :Kevin Ding;
 * @TIME :2020/8/12;
 * @TODO :图片上传;
 */
@Service
public class UploadService {
    @Resource
    private Dao mysqlDao;

    public List<String> uploadImg(MultipartFile[] file, HttpServletRequest request){
        String localPath = "E:/BaiduNetdiskDownload/";//上传图片的目标文件夹
        List<String> fileURL = new ArrayList<>();//存放保存的图片的路径

        int num = 0;
        for (MultipartFile multipartFile : file) {
            String fileName = multipartFile.getOriginalFilename();//获取原文件名
            fileName = FileNameUtils.getFileName(fileName);//生成新的文件名，防止重名问题

            if (FileNameUtils.upload(multipartFile,localPath,fileName)){//上传文件到自定义的本地E盘
                num++;
                //生成可访问的url
                String URL = request.getRequestURL().substring(0,22) + "BaiduNetdiskDownload/" + fileName;
                fileURL.add(URL);
            }
        }

        System.out.println("上传了" + num + "张照片");

        return fileURL;
    }
}
