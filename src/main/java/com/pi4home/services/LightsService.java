package com.pi4home.services;

import com.pi4home.model.lights.Light;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static java.util.Arrays.asList;

@Service
public class LightsService
{
    @Autowired
    Light entranceLight;

    @Autowired
    Light sidewalkLight;

    List<Light> lightList = Arrays.asList(entranceLight, sidewalkLight);

    public Light switchLight(String name)
    {
        Light lightByName = getLightByName(name);

        if (lightByName.isTurnedOn())
        {
            lightByName.turnOffTheLights();
        }

        else if (!lightByName.isTurnedOn())
        {
            lightByName.turnOnTheLight();
        }
        return lightByName;
    }



    public void handleLightDissimilarity(Light lightRq)
    {
        Light lightByName = getLightByName(lightRq.getName());

        if(lightRq.isTurnedOn() && !lightByName.isTurnedOn())
        {
            lightByName.turnOnTheLight();
        }

        if(!lightRq.isTurnedOn() && lightByName.isTurnedOn())
        {
            lightByName.turnOffTheLights();
        }
    }

    private Light getLightByName(String name)
    {
        return lightList
                .stream()
                .filter(light -> light.getName().equals(name))
                .findFirst()
                .orElseThrow(()-> new NoSuchElementException());
    }

    public List<Light> getLightList()
    {
        return asList(entranceLight);
    }
}
