package com.bds.redissondemo.config;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by guoyu on 2018/1/29.
 */

@Configuration
public class DruidDataSourceConfig {
	@Autowired
    Environment environment;
	public static Logger logger = LoggerFactory.getLogger(DruidDataSourceConfig.class);

	@Bean(name = "mysqlDruid")
	@Qualifier("mysqlDataSource")
	public DataSource mysqlDruidDataSource() throws Exception {
		Properties properties = new Properties();
		properties.put("url", environment.getProperty("spring.datasource.url"));
		properties.put("username", environment.getProperty("spring.datasource.username"));
		properties.put("password", environment.getProperty("spring.datasource.password"));
		properties.put("driver", "com.mysql.jdbc.Driver");
		properties.put("type", "com.alibaba.druid.pool.DruidDataSource");
		logger.info("datasource:"+properties);
		return DruidDataSourceFactory.createDataSource(properties);
	}
}
