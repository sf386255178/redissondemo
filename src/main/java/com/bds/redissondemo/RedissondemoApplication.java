package com.bds.redissondemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync//开始异步调用
@SpringBootApplication
public class RedissondemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedissondemoApplication.class, args);
	} 

}
