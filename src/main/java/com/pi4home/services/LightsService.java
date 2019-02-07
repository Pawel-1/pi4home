package com.pi4home.services;

import com.pi4home.model.lights.Light;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Arrays.asList;

@Service
public class LightsService
{
    @Autowired
    Light entranceLight;

    public Light switchLight()
    {
        if (entranceLight.isTurnedOn())
        {
            entranceLight.turnOffTheLights();
        }

        else if (!entranceLight.isTurnedOn())
        {
            entranceLight.turnOnTheLight();
        }
        return entranceLight;
    }

    public void handleLightDissimilarity(Light lightRq)
    {
        if(lightRq.isTurnedOn() && !entranceLight.isTurnedOn())
        {
            entranceLight.turnOnTheLight();
        }

        if(!lightRq.isTurnedOn() && entranceLight.isTurnedOn())
        {
            entranceLight.turnOffTheLights();
        }
    }


    public List<Light> getLightList()
    {
        return asList(entranceLight);
    }
}
