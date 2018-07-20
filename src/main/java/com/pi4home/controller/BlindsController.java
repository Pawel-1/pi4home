package com.pi4home.controller;

import com.pi4home.services.BlindsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlindsController
{
    @Autowired
    private BlindsService blindsService;

    @RequestMapping("/blinds")
    public String blinds()
    {
        blindsService.getPinBlindLargeWindowLeft().toggle();
        return "blinds toggled";
    }

}
