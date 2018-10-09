package com.pi4home.model.blinds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.pi4home.jpa.BlindStateRepository;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.NoSuchElementException;

public class Blind
{
    @JsonIgnore
    @Autowired
    private BlindStateRepository blindStateRepository;

    @JsonIgnore
    private static final int BLIND_MOVEMENT_TIME = 31000;
    @JsonIgnore
    private GpioPinDigitalOutput goUpPin;
    @JsonIgnore
    private GpioPinDigitalOutput goDownPin;
    private String name;
    private BlindState blindState;

    public void setMasking(BlindState updatedBlindState) throws InterruptedException
    {
        BlindState actualBlindState = this.getBlindState();

        int maskingState = actualBlindState.getPercentageMaskingState();

        int updatedMaskingState = updatedBlindState.getPercentageMaskingState();

        if (maskingState > updatedMaskingState)
        {
            int percentageToMove = (maskingState - updatedMaskingState) / 100;
            System.out.print(this.getName() + " goes up for TIME: " + BLIND_MOVEMENT_TIME * percentageToMove);
            blindGoesUp(BLIND_MOVEMENT_TIME * percentageToMove);
        }
        else
        {
            int percentageToMove = (updatedMaskingState - maskingState) / 100;
            System.out.print(this.getName() + " goes down for TIME: " + BLIND_MOVEMENT_TIME * percentageToMove);
            blindGoesDown(BLIND_MOVEMENT_TIME * percentageToMove);
        }
        this.setBlindState(updatedBlindState);
    }

    private void blindGoesDown(int millis) throws InterruptedException
    {
        setHighStateOnPin(goDownPin, millis);
    }


    private void blindGoesUp(int millis) throws InterruptedException
    {
        setHighStateOnPin(goUpPin, millis);
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
        BlindState blindStateDb = blindStateRepository
                .findById(this.getName())
                .orElseThrow(() -> new NoSuchElementException());

        blindState = blindStateDb;
        return blindState;
    }

    public void setBlindState(BlindState blindState)
    {
        this.blindState = blindState;
        blindStateRepository.save(blindState);
    }
}
