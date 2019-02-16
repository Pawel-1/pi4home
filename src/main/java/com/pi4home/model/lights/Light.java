package com.pi4home.model.lights;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Light
{
    @JsonIgnore
    private Logger logger = LoggerFactory.getLogger(getClass());

    private String name;
    @JsonIgnore
    private GpioPinDigitalOutput lightOnPin;
    boolean isTurnedOn;

    public void turnOnTheLight()
    {
        lightOnPin.high();
        isTurnedOn = true;
        logger.info(this.getName() + "is ON");
    }

    public void turnOffTheLights()
    {
        lightOnPin.low();
        isTurnedOn = false;
        logger.info(this.getName() + "is OFF");
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

    public String getName()
    {
        return name;
    }
}
