package com.pi4home.model;

import com.pi4home.enums.BlindState;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import org.springframework.stereotype.Component;

@Component
public class Blind
{
    GpioPinDigitalOutput goUpPin;
    GpioPinDigitalOutput goDownPin;
    BlindState blindState;
    String name;

    public GpioPinDigitalOutput getGoUpPin()
    {
        return goUpPin;
    }

    public void setGoUpPin(GpioPinDigitalOutput goUpPin)
    {
        this.goUpPin = goUpPin;
    }

    public GpioPinDigitalOutput getGoDownPin()
    {
        return goDownPin;
    }

    public void setGoDownPin(GpioPinDigitalOutput goDownPin)
    {
        this.goDownPin = goDownPin;
    }

    public BlindState getBlindState()
    {
        return blindState;
    }

    public void setBlindState(BlindState blindState)
    {
        this.blindState = blindState;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
