package com.pi4home.configurations;

import com.pi4home.model.yeelight.YeelightDeviceWrapper;
import com.pi4home.api.yapi.YeelightDevice;
import com.pi4home.api.yapi.exception.YeelightSocketException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class YeelightFactoryBeanAppConfig
{

    @Bean(name = "deskYeelight")
    public YeelightDeviceWrapper deskYeelight()
    {
        YeelightDevice deskLightYeelightDevice = null;
        try
        {
            deskLightYeelightDevice = new YeelightDevice("192.168.0.192");
        }
        catch (YeelightSocketException e)
        {
            e.printStackTrace();
        }

        YeelightDeviceWrapper deskLightYeelightDeviceWrapper = new YeelightDeviceWrapper();
        deskLightYeelightDeviceWrapper.setYeelightDevice(deskLightYeelightDevice);
        deskLightYeelightDeviceWrapper.setName("deskYeelight");

        return deskLightYeelightDeviceWrapper;
    }

    @Bean(name = "rightYeelight")
    public YeelightDeviceWrapper rightYeelight()
    {
        YeelightDevice rightYeelightDevice = null;
        try
        {
            rightYeelightDevice = new YeelightDevice("192.168.0.191");
        }
        catch (YeelightSocketException e)
        {
            e.printStackTrace();
        }

        YeelightDeviceWrapper deskLightYeelightDeviceWrapper = new YeelightDeviceWrapper();
        deskLightYeelightDeviceWrapper.setYeelightDevice(rightYeelightDevice);
        deskLightYeelightDeviceWrapper.setName("rightYeelight");

        return deskLightYeelightDeviceWrapper;
    }

    @Bean(name = "leftYeelight")
    public YeelightDeviceWrapper leftYeelight()
    {
        YeelightDevice leftYeelightDevice = null;
        try
        {
            leftYeelightDevice = new YeelightDevice("192.168.0.190");
        }
        catch (YeelightSocketException e)
        {
            e.printStackTrace();
        }

        YeelightDeviceWrapper deskLightYeelightDeviceWrapper = new YeelightDeviceWrapper();
        deskLightYeelightDeviceWrapper.setYeelightDevice(leftYeelightDevice);
        deskLightYeelightDeviceWrapper.setName("leftYeelight");

        return deskLightYeelightDeviceWrapper;
    }
}
