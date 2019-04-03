package com.pi4home.messageBroker;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pi4home.model.blinds.Blind;
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
        ObjectMapper objectMapper = new ObjectMapper();
        try
        {
            Light light = objectMapper.readValue(brokerMessage, Light.class);
            lightsService.updateLightState(light);

            logger.info("Message from broker consumed: " + light.getName()
                    + " is turned on: " + light.isTurnedOn());
        }

        catch (JsonMappingException e)
        {
            logger.warn("cannot map JSON to NotificationRequest: " + brokerMessage, e.getMessage());

            try
            {
                Blind blind = objectMapper.readValue(brokerMessage, Blind.class);
                try
                {
                    blindsService.updateBlindState(blind);
                }
                catch (InterruptedException e1)
                {
                    e1.printStackTrace();
                }
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
        }
    }
}
