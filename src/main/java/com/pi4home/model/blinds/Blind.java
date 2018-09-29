package com.pi4home.model.blinds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pi4j.io.gpio.GpioPinDigitalOutput;

import javax.persistence.*;

@Entity
public class Blind
{
    @Transient
    @JsonIgnore
    private static final int BLIND_MOVEMENT_TIME = 31000;
    @Transient
    @JsonIgnore
    private GpioPinDigitalOutput goUpPin;
    @Transient
    @JsonIgnore
    private GpioPinDigitalOutput goDownPin;
    @Id
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    private BlindState blindState;

    public Blind()
    {
        this.blindState = BlindState.uncovered();
    }

    public void setMasking(BlindState updatedBlindState) throws InterruptedException
    {
        int maskingState = blindState.getPercentageMaskingState();
        int updatedMaskingState = updatedBlindState.getPercentageMaskingState();

        if (maskingState > updatedMaskingState)
        {
            int percentageToMove = maskingState - updatedMaskingState / 100;
            System.out.print(this.getName() + " goes up for TIME: " + BLIND_MOVEMENT_TIME * percentageToMove);
            blindGoesUp(BLIND_MOVEMENT_TIME * percentageToMove);
        }
        else
        {
            int percentageToMove = updatedMaskingState - maskingState / 100;
            System.out.print(this.getName() + " goes down for TIME: " + BLIND_MOVEMENT_TIME * percentageToMove);
            blindGoesDown(BLIND_MOVEMENT_TIME * percentageToMove);
        }
        blindState.setPercentageMaskingState(updatedMaskingState);
    }

    private void blindGoesDown(int millis) throws InterruptedException
    {
        setHighStateOnPin(goDownPin, millis);
        System.out.println("Blind " + this.getName() + " goes down" + " BLIND STATE : " + this.getBlindState());
    }


    private void blindGoesUp(int millis) throws InterruptedException
    {
        setHighStateOnPin(goUpPin, millis);
        System.out.println("Blind " + this.getName() + " goes up" + " BLIND STATE : " + this.getBlindState());
    }


    private void setHighStateOnPin(GpioPinDigitalOutput digitalPin, int millis) throws InterruptedException
    {
        digitalPin.high();
        Thread.sleep(millis);
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
