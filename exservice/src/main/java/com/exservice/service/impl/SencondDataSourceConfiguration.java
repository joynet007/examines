package com.exservice.service.impl;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 主要提供两个数据源
 */
//@Configuration
public class SencondDataSourceConfiguration {
//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource newDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "secondDatasource")
//    @ConfigurationProperties(prefix = "spring.second-datasource")
//    public DataSource secondDataSource() {
//        return DataSourceBuilder.create().build();
//    }
}