package com.salinity.wms.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;


/**
 * 创建消息队列
 * Created by Administrator on 2017/6/14.
 */
@Configuration
public class QueueConfig {

    @Bean
    public Queue logQueue() {
        return new ActiveMQQueue("LOG_QUEUE");
    }
}
