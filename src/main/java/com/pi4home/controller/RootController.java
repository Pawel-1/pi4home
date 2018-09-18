package com.pi4home.controller;

import com.pi4home.services.BlindsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
public class RootController
{
    @Autowired
    private BlindsService blindsService;

    @RequestMapping("/")
    public String root()
    {
        return blindsService.getBlindList()
                .stream()
                .collect(Collectors.toMap(blind -> blind.getName(),
                        blind -> blind.getBlindState()))
                .entrySet()
                .stream()
                .map(e ->
                {
                    return e.getKey() + "  |  " + e.getValue() + "\n";
                })
                .map(s -> s + "\n")
                .collect(Collectors.joining("\n"));
    }
}
