package com.pi4home.controller;

import com.pi4home.services.BlindsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlindsController
{
    @Autowired
    private BlindsService blindsService;

    @ModelAttribute
    @RequestMapping("/blinds")
    public String blinds()
    {
        return "blinds";
    }

    @RequestMapping("/blinds/{name}")
    public String blinds(@PathVariable String name)
    {
        try
        {
            blindsService.toggleBlindState(name);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        return name + " has been toggled";
    }

}
