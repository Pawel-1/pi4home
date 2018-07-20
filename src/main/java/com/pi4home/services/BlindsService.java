package com.pi4home.services;

import com.pi4j.io.gpio.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BlindsService
{
    GpioController gpioController = GpioFactory.getInstance();

    //ToDo: wrap RaspiPin, name and state
    private List<GpioPinDigitalOutput> gpioPinDigitalOutputs = Arrays.asList(
            gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_01, "pinBlindLargeWindowLeft", PinState.LOW),
            gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_02, "pinBlindLargeWindowRight", PinState.LOW),
            gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_03, "pinBlindSmallWindowLeft", PinState.LOW),
            gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_04, "pinBlindSmallWindowMiddle", PinState.LOW),
            gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_05, "pinBlindSmallWindowRight", PinState.LOW));


    private GpioPinDigitalOutput getPinByName(String pinName)
    {
        return gpioPinDigitalOutputs
                .stream()
                .filter(gpioPinDigitalOutput -> gpioPinDigitalOutput.getName().equals(pinName))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException());
    }

    public void togglePinState(String pinName)
    {
        getPinByName(pinName).toggle();
    }
}
