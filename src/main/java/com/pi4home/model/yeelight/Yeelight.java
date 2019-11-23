package com.pi4home.model.yeelight;

public class Yeelight
{
    private String name;
    private boolean isTurnedOn;
    private RGB rgb;
    private int brightness;
    private int hue;
    private int saturation;
    private boolean returnProperties;


    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public boolean isTurnedOn()
    {
        return isTurnedOn;
    }

    public void setTurnedOn(boolean turnedOn)
    {
        this.isTurnedOn = turnedOn;
    }

    public RGB getRgb()
    {
        return rgb;
    }

    public void setRgb(RGB rgb)
    {
        this.rgb = rgb;
    }

    public int getBrightness()
    {
        return brightness;
    }

    public void setBrightness(int brightness)
    {
        this.brightness = brightness;
    }

    public int getHue()
    {
        return hue;
    }

    public void setHue(int hue)
    {
        this.hue = hue;
    }

    public int getSaturation()
    {
        return saturation;
    }

    public void setSaturation(int saturation)
    {
        this.saturation = saturation;
    }

    public boolean isReturnProperties()
    {
        return returnProperties;
    }

    public void setReturnProperties(boolean returnProperties)
    {
        this.returnProperties = returnProperties;
    }
}
