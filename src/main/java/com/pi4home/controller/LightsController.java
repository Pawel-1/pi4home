package com.pi4home.controller;

import com.pi4home.services.LightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LightsController
{
    @Autowired
    LightsService lightsService;

    @RequestMapping("/light")
    public void light()
    {
        lightsService.switchLight();
    }
}
