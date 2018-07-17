package com.pi4home.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController
{
    @RequestMapping("/")
    public String root()
    {
        return "Pi4Home up and running!";
    }
}
