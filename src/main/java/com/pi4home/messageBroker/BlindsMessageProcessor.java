package com.pi4home.messageBroker;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pi4home.model.blinds.Blind;
import com.pi4home.services.BlindsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class BlindsMessageProcessor implements Runnable
{
    @Autowired
    private BlindsService blindsService;
    private Logger logger = LoggerFactory.getLogger(getClass());
    private String brokerMessage;

    public BlindsMessageProcessor(String brokerMessage)
    {
        this.brokerMessage = brokerMessage;
    }

    @Override
    public void run()
    {
        Blind blind = deserializeBlindObject(brokerMessage);

        try
        {
            blindsService.updateBlindState(blind);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    private Blind deserializeBlindObject(String brokerMessage)
    {
        ObjectMapper objectMapper = new ObjectMapper();

        Blind blind = null;
        try
        {
            blind = objectMapper.readValue(brokerMessage, Blind.class);
        }

        catch (JsonMappingException e)
        {
            logger.warn("cannot map JSON to NotificationRequest: " + brokerMessage, e.getMessage());
        }

        catch (IOException e1)
        {
            e1.printStackTrace();
        }
        return blind;
    }
}
