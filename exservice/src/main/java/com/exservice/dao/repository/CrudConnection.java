package com.exservice.dao.repository;

import com.excomm.helper.SqlVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Created by liang on 2018/7/8.
 */
@Component
public class CrudConnection {

    @Value("${spring.datasource.driverClassName}")
    String driverClass="";

    @Value("${spring.datasource.url}")
    String url="";

    @Value("${spring.datasource.username}")
    String username="";

    @Value("${spring.datasource.password}")
    String password="";


    public DataSource getDataSource(){

        System.out.println("--"+driverClass+"--"+url+"--"+username+"--"+password);

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }



    public Connection getConnection(){
        DataSource dataSource = getDataSource();
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

}
