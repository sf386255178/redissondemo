package com.bds.redissondemo.utils;

/**
 * @Author :Kevin Ding;
 * @TIME :2021/3/11;
 * @TODO :识别pdf;
 */

import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 功能 PDF读写类
 * @CreateTime 2011-4-14 下午02:44:11
 */
public class PDFUtil {



    /**
     * 读PDF文件，使用了pdfbox开源项目
     * @param fileName
     */
    public static  void readPDF(String fileName) {
        File file = new File(fileName);
        FileInputStream in = null;
        try {
            in = new FileInputStream(fileName);
            // 新建一个PDF解析器对象
            PDFParser parser = new PDFParser(new RandomAccessFile(file,"rw"));
            // 对PDF文件进行解析
            parser.parse();
            // 获取解析后得到的PDF文档对象
            PDDocument pdfdocument = parser.getPDDocument();
            // 新建一个PDF文本剥离器
            PDFTextStripper stripper = new PDFTextStripper();
            stripper .setSortByPosition(false); //sort设置为true 则按照行进行读取，默认是false
            // 从PDF文档对象中剥离文本
            String result = stripper.getText(pdfdocument);
            FileWriter fileWriter = new FileWriter(new File("pdf.txt"));
            fileWriter.write(result);
            fileWriter.flush();
            fileWriter.close();
            System.out.println("PDF文件的文本内容如下：");
            System.out.println(result);

        } catch (Exception e) {
            System.out.println("读取PDF文件" + file.getAbsolutePath() + "生失败！" + e);
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                }
            }
        }
    }


    /**
     * 测试pdf文件的创建
     * @param args
     */
    public static void main(String[] args) {

        String fileName = "D:\\Documents\\WeChat Files\\wxid_jp6rhf76bkoq21\\FileStorage\\File\\2021-03\\08063155.pdf";  //这里先手动把绝对路径的文件夹给补上。
//        PDFUtil pdfUtil = new PDFUtil();
        readPDF(fileName);
    }
}

