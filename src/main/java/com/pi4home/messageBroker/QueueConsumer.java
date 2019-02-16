package com.pi4home.messageBroker;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pi4home.model.lights.Light;
import com.pi4home.services.LightsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueueConsumer
{

    @Autowired
    LightsService lightsService;

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

    private void processMessage(String message)
    {
        logger.info("Processing message from broker");
        Light light = null;
        try
        {
            light = new ObjectMapper().readValue(message, Light.class);
            lightsService.handleLightDissimilarity(light);
        }
        catch (JsonParseException e)
        {
            logger.warn("Bad JSON in message: " + message);
        }
        catch (JsonMappingException e)
        {
            logger.warn("cannot map JSON to NotificationRequest: " + message);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
        }
        logger.info("Message from broker consumed: " + light.getName()
                + " is turned on: " + light.isTurnedOn());

    }
}
