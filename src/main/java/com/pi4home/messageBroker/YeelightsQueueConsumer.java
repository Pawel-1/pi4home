package com.pi4home.messageBroker;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pi4home.model.yeelight.Yeelight;
import com.pi4home.services.YeelightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@PropertySource(ignoreResourceNotFound = true, value = "classpath:application.properties")
@Component
public class YeelightsQueueConsumer
{

    @Autowired
    YeelightService yeelightService;

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
        ObjectMapper objectMapper = new ObjectMapper();

        Yeelight yeelight = null;

        try
        {
            yeelight = objectMapper.readValue(brokerMessage, Yeelight.class);
        }
        catch (JsonMappingException e)
        {
            logger.warn("cannot map JSON to NotificationRequest: " + brokerMessage, e.getMessage());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        yeelightService.updateYeelightStatus(yeelight);

        logger.info("Message from broker consumed: " + yeelight.getName()
                + " is turned on: " + yeelight.isTurnedOn());    }
}
