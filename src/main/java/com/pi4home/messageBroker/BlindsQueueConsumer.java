package com.pi4home.messageBroker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource(ignoreResourceNotFound = true, value = "classpath:application.properties")
@Component
public class BlindsQueueConsumer
{
    @Autowired
    BlindsMessageProcessor blindsMessageProcessor;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public void receiveMessageBlinds(String message)
    {
        logger.info("Received (String) " + message);
        blindsMessageProcessor.setBrokerMessage(message);
        Thread thread = new Thread(blindsMessageProcessor);
        thread.start();
    }
}
