package com.pi4home.model.lights;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pi4j.io.gpio.GpioPinDigitalOutput;

public class Light
{
    private String name;
    @JsonIgnore
    private GpioPinDigitalOutput lightOnPin;
    boolean isTurnedOn;

    public void turnOnTheLight()
    {
        lightOnPin.toggle();
        isTurnedOn = true;
    }

    public void turnOffTheLights()
    {
        lightOnPin.low();
        isTurnedOn = false;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setLightOnPin(GpioPinDigitalOutput lightOnPin)
    {
        this.lightOnPin = lightOnPin;
    }

    public void setTurnedOn(boolean turnedOn)
    {
        isTurnedOn = turnedOn;
    }

    public boolean isTurnedOn()
    {
        return isTurnedOn;
    }
}