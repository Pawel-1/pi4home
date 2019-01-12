package com.pi4home.services;

import com.pi4home.model.lights.Light;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LightsService
{
    @Autowired
    Light entranceLight;

    public void switchLight()
    {
        if (entranceLight.isTurnedOn())
        {
            entranceLight.turnOffTheLights();
        }

        else if (!entranceLight.isTurnedOn())
        {
            entranceLight.turnOnTheLight();
        }
    }
}
