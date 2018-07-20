package com.pi4home.controller;

import com.pi4j.io.gpio.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlindsController
{
    GpioController gpioController = GpioFactory.getInstance();
    private static GpioPinDigitalOutput pinDigitalOutput;

    @RequestMapping("/blinds")
    public String blinds()
    {
        if (pinDigitalOutput == null)
        {
            pinDigitalOutput = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_01, "blinds", PinState.LOW);
        }

        pinDigitalOutput.toggle();
        return "blinds toggled";
    }

}
