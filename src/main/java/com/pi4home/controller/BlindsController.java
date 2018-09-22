package com.pi4home.controller;

import com.pi4home.blinds.Blind;
import com.pi4home.services.BlindsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class BlindsController
{
    @Autowired
    private BlindsService blindsService;

    @RequestMapping("/blinds")
    public List<Blind> blinds(Model model)
    {
        return blindsService.getBlindList();
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
        return name + " blinds";
    }

}
