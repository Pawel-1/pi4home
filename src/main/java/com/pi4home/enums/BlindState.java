package com.pi4home.enums;

public enum BlindState
{
    UP("UP"),
    DOWN("DOWN");

    private String name;

    BlindState(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return this.name;
    }

}
