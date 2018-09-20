package com.pi4home.controller;

import com.pi4home.blinds.Blind;
import com.pi4home.services.BlindsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BlindsController
{
    @Autowired
    private BlindsService blindsService;

    @RequestMapping("/blinds")
    public String blinds(Model model)
    {
        List<Blind> blindList = blindsService.getBlindList();
        model.addAttribute("blindList", blindList);


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
