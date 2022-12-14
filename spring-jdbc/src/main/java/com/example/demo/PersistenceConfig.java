package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class PersistenceConfig {
    @Value( "${jdbc.url}" )
    private String jdbcUrl;

    @Value( "${mysql.driver}" )
    private String mysqlDriver;

    @Value( "${mysql.user}" )
    private String user;

    @Value( "${mysql.password}" )
    private String password;


    @Bean
    public DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(mysqlDriver);
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        System.out.println("PersistenceConfig::mysqlDataSource url=" + jdbcUrl);
        return dataSource;
    }
}
