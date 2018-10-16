package com.pi4home.model.blinds;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BLIND_STATE")
public class BlindState
{
    @Id
    private String blindName;
    private double percentageMaskingState;

    public BlindState()
    {

    }

    public BlindState(String blindName, int percentageMaskingState)
    {
        this.blindName = blindName;
        this.percentageMaskingState = percentageMaskingState;
    }

    public static BlindState covered(String blindName)
    {
        return new BlindState(blindName, 100);
    }

    public static BlindState uncovered(String blindName)
    {
        return new BlindState(blindName, 0);
    }

    public double getPercentageMaskingState()
    {
        return percentageMaskingState;
    }

    public void setPercentageMaskingState(int percentageMaskingState)
    {
        this.percentageMaskingState = percentageMaskingState;
    }

    public String getBlindName()
    {
        return blindName;
    }
}
