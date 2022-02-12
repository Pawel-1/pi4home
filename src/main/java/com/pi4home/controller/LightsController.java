package com.pi4home.controller;

import com.pi4home.model.lights.Light;
import com.pi4home.services.LightsService;
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

    @RequestMapping("/light/{name}")
    public Light light(@PathVariable String name)
    {
        return lightsService.switchLight(name);
    }

    @RequestMapping("/entranceLight/switchOn")
    public void entranceLightSwitchOn()
    {
        lightsService.updateDb(true);
    }


    @RequestMapping("/entranceLight/switchOff")
    public void entranceLightSwitchOff()
    {
        lightsService.updateDb(false);
    }
}
