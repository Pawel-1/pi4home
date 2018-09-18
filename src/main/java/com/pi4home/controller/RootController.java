package com.pi4home.controller;

import com.pi4home.blinds.Blind;
import com.pi4home.enums.BlindState;
import com.pi4home.services.BlindsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class RootController
{
    @Autowired
    private BlindsService blindsService;

    @RequestMapping("/")
    public String root()
    {
        Map<String, BlindState> blindNameAndStateMap =
                blindsService.getBlindList()
                        .stream()
                        .collect(Collectors.toMap(blind -> blind.getName(),
                                blind -> blind.getBlindState()));

        List<String> list = blindsService.getBlindList()
                .stream()
                .map(Blind::getName)
                .collect(Collectors.toList());

        String stringList = String.join(", ", list);

        String collect = list
                .stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));

        return collect;
    }
}
