package com.pi4home.controller;

import com.pi4j.io.gpio.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController
{
    final GpioController gpio = GpioFactory.getInstance();
    final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_16, "MyLED", PinState.HIGH);
    final GpioPinDigitalOutput pin2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "MyLED", PinState.HIGH);

    @RequestMapping("/test")
    public String root() throws InterruptedException
    {
        System.out.println("Root for simple E2E testing");

        pin.setShutdownOptions(true, PinState.LOW);
        pin2.setShutdownOptions(true, PinState.LOW);

        System.out.println("--> GPIO state should be: ON");

        Thread.sleep(5000);

        pin.low();
        pin2.low();

        System.out.println("--> GPIO state should be: OFF");

        Thread.sleep(5000);

        gpio.shutdown();

        System.out.println("Exiting testing mode");

        return "pi4 home E2E test";
    }
}
