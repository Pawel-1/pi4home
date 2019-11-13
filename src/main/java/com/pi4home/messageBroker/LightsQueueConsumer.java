package com.pi4home.messageBroker;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pi4home.model.lights.Light;
import com.pi4home.services.LightsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@PropertySource(ignoreResourceNotFound = true, value = "classpath:application.properties")
@Component
public class LightsQueueConsumer
{

    @Autowired
    LightsService lightsService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public void receiveMessageLights(String message)
    {
        logger.info("Received (String) " + message);
        processMessage(message);
    }

    public void receiveMessageLights(byte[] message)
    {
        String strMessage = new String(message);
        logger.info("Received (No String) " + strMessage);
        processMessage(strMessage);
    }


    private void processMessage(String brokerMessage)
    {
        ObjectMapper objectMapper = new ObjectMapper();

        Light light = null;

        try
        {
            light = objectMapper.readValue(brokerMessage, Light.class);
        }

        catch (JsonMappingException e)
        {
            logger.warn("cannot map JSON to NotificationRequest: " + brokerMessage, e.getMessage());
        }

        catch (IOException e1)
        {
            e1.printStackTrace();
        }

        lightsService.updateLightState(light);

        logger.info("Message from broker consumed: " + light.getName()
                + " is turned on: " + light.isTurnedOn());
    }
}
