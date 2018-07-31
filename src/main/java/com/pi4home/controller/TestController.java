package com.pi4home.controller;

import com.pi4j.io.gpio.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController
{
    @RequestMapping("/test")
    public String root() throws InterruptedException
    {
        System.out.println("Root for simple E2E testing");

        final GpioController gpio = GpioFactory.getInstance();

        final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_21, "MyLED", PinState.HIGH);

        pin.setShutdownOptions(true, PinState.LOW);

        System.out.println("--> GPIO state should be: ON");

        Thread.sleep(5000);

        pin.low();
        System.out.println("--> GPIO state should be: OFF");

        Thread.sleep(5000);

        gpio.shutdown();

        System.out.println("Exiting testing mode");

        return "pi4 home E2E test";
    }
}
