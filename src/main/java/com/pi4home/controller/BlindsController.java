package com.pi4home.controller;

import com.pi4home.blinds.Blind;
import com.pi4home.services.BlindsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlindsController
{
    @Autowired
    private BlindsService blindsService;

    @RequestMapping("/blinds")
    public List<Blind> blinds()
    {
        return blindsService.getBlindList();
    }


    @RequestMapping("/blinds/{name}")
    public Blind blinds(@PathVariable String name)
    {
        try
        {
            blindsService.toggleBlindState(name);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        return blindsService.getBlindByName(name);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/blinds/{name}")
    public void updateBlindState(@RequestBody Blind blind, @PathVariable String name)
    {
        try
        {
            blindsService.updateBlindState(blind);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
