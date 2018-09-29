package com.pi4home.model.blinds;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BlindState
{
    @Id
    @GeneratedValue
    private int id;
    private int percentageMaskingState;

    public BlindState()
    {

    }

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
