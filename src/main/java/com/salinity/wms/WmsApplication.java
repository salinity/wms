package com.salinity.wms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration//启动自动配置
@ComponentScan//扫描组件
@EnableJms
public class WmsApplication extends SpringBootServletInitializer{

    public static void main(String[] args) {
        SpringApplication.run(WmsApplication.class, args);
        System.out.println();
        System.out.print("--------------------");
        System.out.print("启动成功");
        System.out.print("--------------------");
        System.out.println();
    }
}
