package com.salinity.wms.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.sql.SQLException;

/**
 * Created by Administrator on 2017/6/13.
 */
@Configuration
public class DataSourcesConfig {

    @Primary//默认数据源
    @Bean(name ="dataSource",destroyMethod = "close")
    public DruidDataSource Construction() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/wms?useUnicode=true&characterEncoding=utf-8&useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        //配置最大连接
        dataSource.setMaxActive(20);
        //配置初始连接
        dataSource.setInitialSize(1);
        //配置最小连接
        dataSource.setMinIdle(1);
        //连接等待超时时间
        dataSource.setMaxWait(6000);
        //间隔多久进行检测，关闭空闲连接
        dataSource.setTimeBetweenEvictionRunsMillis(6000);
        //一个链接最小生存时间
        dataSource.setMinEvictableIdleTimeMillis(300000);
        //用来检测是否有效的sql
        dataSource.setValidationQuery("select 'x'");
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        //打开PSCache,并指定每个链接的PSCache大小
        dataSource.setPoolPreparedStatements(true);
        dataSource.setMaxOpenPreparedStatements(20);
        //配置sql监控的filter
        dataSource.setFilters("stat,wall,log4j");
        try{
            dataSource.init();
        }catch (SQLException e){
            throw new RuntimeException("druid datasource init fail");
        }
        System.out.println();
        System.out.print("--------------------");
        System.out.print("数据库连接配置成功");
        System.out.print("--------------------");
        System.out.println();
        return dataSource;
    }
}
