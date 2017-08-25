package com.salinity.wms.activemq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/6/14.
 */
@Component
public class LogConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogConsumer.class);

    @JmsListener(destination = "LOG_QUEUE")
    public void receiveQueue(String msg) {
        LOGGER.info("Has received from LOG_QUEUE msg" + msg);
    }
}
