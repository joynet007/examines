package com.exservice.dao.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

/**
 * Created by liang on 2018/7/9.
 */


public class DbProperties {

    @Value("${spring.datasource.url}")
    String driverClass="com.mysql.jdbc.Driver";

    @Value("${spring.datasource.url}")
    String url="jdbc:mysql://localhost:3306/ruankao?characterEncoding=utf8";

    String username="root";

    String password="root123";

}
