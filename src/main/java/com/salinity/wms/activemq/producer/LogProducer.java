package com.salinity.wms.activemq.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

/**
 * Created by Administrator on 2017/6/14.
 */
@Component
public class LogProducer implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogProducer.class);

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue logQueue;

    @Override
    public void run(String... strings) throws Exception {
        if (strings.length > 0) {
//            send(strings[0]);
        } else
            send("This is a log message");
            LOGGER.info("Log Message was sent to Queue named sample.log");
    }

    public void send(String msg) {
        this.jmsMessagingTemplate.convertAndSend(this.logQueue, msg);
    }
}
