package com.pi4home.messageBroker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource(ignoreResourceNotFound = true, value = "classpath:application.properties")
@Component
public class YeelightsQueueConsumer
{


    private Logger logger = LoggerFactory.getLogger(getClass());

    public void receiveMessageYeelights(String message)
    {
        logger.info("Received (String) " + message);
        processMessage(message);
    }

    public void receiveMessageYeelights(byte[] message)
    {
        String strMessage = new String(message);
        logger.info("Received (No String) " + strMessage);
        processMessage(strMessage);
    }


    private void processMessage(String brokerMessage)
    {
        logger.warn("GET Yeelight message" + brokerMessage);
    }
}
