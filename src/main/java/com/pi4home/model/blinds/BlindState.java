package com.pi4home.model.blinds;

import javax.persistence.Entity;

@Entity
public class BlindState
{

    private int percentageMaskingState;

    public BlindState(int percentageMaskingState)
    {
        this.percentageMaskingState = percentageMaskingState;
    }

    public static BlindState covered()
    {
        return new BlindState(100);
    }

    public static BlindState uncovered()
    {
        return new BlindState(0);
    }

    public int getPercentageMaskingState()
    {
        return percentageMaskingState;
    }

    public void setPercentageMaskingState(int percentageMaskingState)
    {
        this.percentageMaskingState = percentageMaskingState;
    }
}
