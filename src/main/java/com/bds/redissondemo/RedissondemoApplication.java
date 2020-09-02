package com.bds.redissondemo;

import com.bds.redissondemo.utils.Const;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


@EnableAsync//开始异步调用
@SpringBootApplication
public class RedissondemoApplication {

	public static void main(String[] args) {
		//创建一个配置类
		PropertiesConfiguration props = new PropertiesConfiguration();
		try {
			props.setListDelimiter('#');
//			读取application的配置文件信息
			InputStream stream = RedissondemoApplication.class.getClassLoader().getResourceAsStream("application.properties");
			BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));

			props.load(br);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Const.env = props.getString("spring.profiles.active");//赋值
		System.out.println("#### 当前环境 == " + Const.env + "####");

		SpringApplication.run(RedissondemoApplication.class, args);
	} 

}
