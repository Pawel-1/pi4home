package com.pi4home.controller;

import com.pi4home.model.lights.Light;
import com.pi4home.services.LightsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LightsController
{
    @Autowired
    LightsService lightsService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/light/{name}")
    public Light light(@PathVariable String name)
    {
        return lightsService.switchLight(name);
    }

    @RequestMapping("/entranceLight/switchOn")
    public void entranceLightSwitchOn()
    {
        logger.info("Entrance Light switched ON by button press");
        lightsService.updateDb(true);
    }


    @RequestMapping("/entranceLight/switchOff")
    public void entranceLightSwitchOff()
    {
        logger.info("Entrance Light switched OFF by button press");
        lightsService.updateDb(false);
    }
}
