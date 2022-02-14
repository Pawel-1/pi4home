package com.pi4home.services;

import com.pi4home.jpa.LightRepository;
import com.pi4home.model.lights.Light;
import com.pi4home.model.lights.LightStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class LightsService
{
    @Value("${light.entranceLight.url}")
    private String entranceLightUrl;
    @Value("${light.ledTvLight.url}")
    private String ledTvLightUrl;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    LightRepository lightRepository;

    public Light switchLight(String name)
    {
        return null;
    }

    public void updateLightState(Light lightRq)
    {
        boolean turnedOn = lightRq.isTurnedOn();

        try
        {
            restTemplate.getForObject(getUrlToCall(lightRq), LightStatus.class, turnedOn);
        }
        catch (RestClientException exception)
        {
            exception.printStackTrace();
        }
    }

    private String getUrlToCall(Light lightRq)
    {
        String lightName = lightRq.getName();

        switch (lightName)
        {
            case "entranceLight":
                return entranceLightUrl;
            case "ledTv":
                return ledTvLightUrl;
            default:
                return null;
        }
    }

    public void updateDb(boolean lightTurnedOn)
    {
        Light light = new Light();
        light.setName("entranceLight");
        light.setTurnedOn(lightTurnedOn);
        lightRepository.save(light);
    }
}
