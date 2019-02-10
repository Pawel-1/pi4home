package com.pi4home.model.blinds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pi4j.io.gpio.GpioPinDigitalOutput;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "BLINDS")
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
    private Double percentageMaskingState;

    public Blind()
    {
    }

    public void setMasking(double updatedBlindState) throws InterruptedException
    {
        System.out.println("actual masking state is : " + this.getPercentageMaskingState());
        System.out.println("updated masking state is : " + updatedBlindState);

        if (this.getPercentageMaskingState() > updatedBlindState)
        {
            double percentageToMove = (this.getPercentageMaskingState() - updatedBlindState) / 100;
            System.out.println("percentage to move is : " + percentageToMove);
            System.out.println(this.getName() + " goes up for TIME: " + BLIND_MOVEMENT_TIME * percentageToMove);
            blindGoesUp(BLIND_MOVEMENT_TIME * percentageToMove);
        }
        else
        {
            double percentageToMove = (updatedBlindState - this.getPercentageMaskingState()) / 100;
            System.out.println("percentage to move is : " + percentageToMove);
            System.out.println(this.getName() + " goes down for TIME: " + BLIND_MOVEMENT_TIME * percentageToMove);

            blindGoesDown(BLIND_MOVEMENT_TIME * percentageToMove);
        }
        this.setPercentageMaskingState(updatedBlindState);
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

    public double getPercentageMaskingState()
    {
        return percentageMaskingState;
    }

    public void setPercentageMaskingState(double percentageMaskingState)
    {
        this.percentageMaskingState = percentageMaskingState;
    }
}
