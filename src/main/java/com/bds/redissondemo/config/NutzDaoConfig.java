package com.bds.redissondemo.config;

import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * Created by guoyu on 2018/1/29.
 */
@Configuration
@Component("mysqlDao")
public class NutzDaoConfig extends NutDao implements Dao {
//	@Qualifier("mysqlDataSource")
    DataSource mysqlDruidDataSource;

    @Autowired
    public void setDruidDataSource( DataSource druidDataSource) {
        this.mysqlDruidDataSource = druidDataSource;
        setDataSource(druidDataSource);
    }
}

