package com.pi4home.controller;

import com.pi4home.model.blinds.Blind;
import com.pi4home.services.BlindsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlindsController
{
    @Autowired
    private BlindsService blindsService;

    @RequestMapping("/initDB")
    public String initDb()
    {
        blindsService.initDb();
        return "DB initialized";
    }

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
    public void updateBlindState(@RequestBody Blind blind)
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
