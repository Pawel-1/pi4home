package com.pi4home.blinds;

import com.pi4home.enums.BlindState;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


public class Blind
{
    private static final long BLIND_MOVEMENT_TIME = 5000;
    private GpioPinDigitalOutput goUpPin;
    private GpioPinDigitalOutput goDownPin;
    private BlindState blindState;
    private String name;

    public void blindGoesDown() throws InterruptedException
    {
        checkIfBlindStateIsAlreadyAsRequested(BlindState.DOWN);

        setHighStateOnPin(goDownPin);
        this.setBlindState(BlindState.DOWN);
    }


    public void blindGoesUp() throws InterruptedException
    {
        checkIfBlindStateIsAlreadyAsRequested(BlindState.UP);

        setHighStateOnPin(goUpPin);
        this.setBlindState(BlindState.UP);
    }

    public void checkIfBlindStateIsAlreadyAsRequested(BlindState blindState)
    {
        if (this.blindState == blindState)
        {
            throw new IllegalStateException("Blind should be already" + blindState.name());
        }
    }

    private void setHighStateOnPin(GpioPinDigitalOutput digitalPin) throws InterruptedException
    {
        digitalPin.high();
        Thread.sleep(BLIND_MOVEMENT_TIME);
        digitalPin.low();
    }

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
