package com.pi4home.messageBroker;

import com.pi4home.services.BlindsService;
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
    BlindsService blindsService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public void receiveMessageBlinds(String message)
    {
        logger.info("Received (String) " + message);
        BlindsMessageProcessor blindsMessageProcessor = new BlindsMessageProcessor(message);
        Thread thread = new Thread(blindsMessageProcessor);
        thread.start();
    }
}
