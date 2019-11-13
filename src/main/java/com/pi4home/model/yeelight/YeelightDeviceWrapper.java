package com.pi4home.model.yeelight;

import com.mollin.yapi.YeelightDevice;

public class YeelightDeviceWrapper
{
    YeelightDevice yeelightDevice;
    String name;

    public YeelightDevice getYeelightDevice()
    {
        return yeelightDevice;
    }

    public void setYeelightDevice(YeelightDevice yeelightDevice)
    {
        this.yeelightDevice = yeelightDevice;
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
