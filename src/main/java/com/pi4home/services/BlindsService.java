package com.pi4home.services;

import com.pi4j.io.gpio.*;
import org.springframework.stereotype.Service;

@Service
public class BlindsService
{
    GpioController gpioController = GpioFactory.getInstance();
    GpioPinDigitalOutput pinBlindLargeWindowLeft = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_01, "blinds", PinState.LOW);
    GpioPinDigitalOutput pinBlindLargeWindowRight = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_02, "blinds", PinState.LOW);
    GpioPinDigitalOutput pinBlindSmallWindowLeft = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_03, "blinds", PinState.LOW);
    GpioPinDigitalOutput pinBlindSmallWindowMiddle = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_04, "blinds", PinState.LOW);
    GpioPinDigitalOutput pinBlindSmallWindowRight = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_05, "blinds", PinState.LOW);

    public GpioPinDigitalOutput getPinBlindLargeWindowLeft()
    {
        return pinBlindLargeWindowLeft;
    }

    public GpioPinDigitalOutput getPinBlindLargeWindowRight()
    {
        return pinBlindLargeWindowRight;
    }

    public GpioPinDigitalOutput getPinBlindSmallWindowLeft()
    {
        return pinBlindSmallWindowLeft;
    }

    public GpioPinDigitalOutput getPinBlindSmallWindowMiddle()
    {
        return pinBlindSmallWindowMiddle;
    }

    public GpioPinDigitalOutput getPinBlindSmallWindowRight()
    {
        return pinBlindSmallWindowRight;
    }

}
