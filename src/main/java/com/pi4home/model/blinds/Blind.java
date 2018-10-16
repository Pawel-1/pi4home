package com.pi4home.model.blinds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pi4j.io.gpio.GpioPinDigitalOutput;

public class Blind
{
    @JsonIgnore
    private static final int BLIND_MOVEMENT_TIME = 31000;
    @JsonIgnore
    private GpioPinDigitalOutput goUpPin;
    @JsonIgnore
    private GpioPinDigitalOutput goDownPin;
    private String name;
    private BlindState blindState;

    public Blind()
    {
    }

    public void setMasking(BlindState updatedBlindState) throws InterruptedException
    {
        BlindState actualBlindState = this.getBlindState();
        double actualMaskingState = actualBlindState.getPercentageMaskingState();
        System.out.println("actual masking state is : " + actualMaskingState);
        double updatedMaskingState = updatedBlindState.getPercentageMaskingState();
        System.out.println("updated masking state is : " + updatedMaskingState);

        if (actualMaskingState > updatedMaskingState)
        {
            double percentageToMove = (actualMaskingState - updatedMaskingState) / 100;
            System.out.println("percentage to move is : " + percentageToMove);
            System.out.println(this.getName() + " goes up for TIME: " + BLIND_MOVEMENT_TIME * percentageToMove);
            blindGoesUp(BLIND_MOVEMENT_TIME * percentageToMove);
        }
        else
        {
            double percentageToMove = (updatedMaskingState - actualMaskingState) / 100;
            System.out.println("percentage to move is : " + percentageToMove);
            System.out.println(this.getName() + " goes down for TIME: " + BLIND_MOVEMENT_TIME * percentageToMove);

            blindGoesDown(BLIND_MOVEMENT_TIME * percentageToMove);
        }
        this.setBlindState(updatedBlindState);
    }

    private void blindGoesDown(double millis) throws InterruptedException
    {
        setHighStateOnPin(goDownPin, millis);
    }


    private void blindGoesUp(double millis) throws InterruptedException
    {
        setHighStateOnPin(goUpPin, millis);
    }


    private void setHighStateOnPin(GpioPinDigitalOutput digitalPin, double millis) throws InterruptedException
    {
        digitalPin.high();
        Thread.sleep((int)millis);
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

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public BlindState getBlindState()
    {
        return blindState;
    }

    public void setBlindState(BlindState blindState)
    {
        this.blindState = blindState;
    }
}
