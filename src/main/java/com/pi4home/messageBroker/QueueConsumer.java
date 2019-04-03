package com.pi4home.messageBroker;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pi4home.model.lights.Light;
import com.pi4home.services.BlindsService;
import com.pi4home.services.LightsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class QueueConsumer
{

    @Autowired
    LightsService lightsService;

    @Autowired
    BlindsService blindsService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public void receiveMessage(String message)
    {
        logger.info("Received (String) " + message);
        processMessage(message);
    }

    public void receiveMessage(byte[] message)
    {
        String strMessage = new String(message);
        logger.info("Received (No String) " + strMessage);
        processMessage(strMessage);
    }

    private void processMessage(String brokerMessage)
    {

        Response response = null;
        try
        {
            response = new ObjectMapper().readValue(brokerMessage, Response.class);
        }
        catch (JsonParseException e)
        {
            logger.warn("Bad JSON in message: " + brokerMessage, e.getMessage());
        }
        catch (JsonMappingException e)
        {
            logger.warn("cannot map JSON to NotificationRequest: " + brokerMessage, e.getMessage());
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
        }
        if(response.response instanceof Light)
        {
            lightsService.handleLightDissimilarity((Light)response.response);
        }

        logger.info("Processing message from broker");
//        try
//        {
//            Light light = new ObjectMapper().readValue(brokerMessage, Light.class);
//            lightsService.handleLightDissimilarity(light);
//
//            logger.info("Message from broker consumed: " + light.getName()
//                    + " is turned on: " + light.isTurnedOn());
//        }
//        catch (JsonParseException e)
//        {
//            logger.warn("Bad JSON in message: " + brokerMessage, e.getMessage());
//        }
//        catch (JsonMappingException e)
//        {
//            logger.warn("cannot map JSON to NotificationRequest: " + brokerMessage, e.getMessage());
//        }
//        catch (Exception e)
//        {
//            logger.error(e.getMessage());
//        }
    }
}
