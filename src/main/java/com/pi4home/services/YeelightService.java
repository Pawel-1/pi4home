package com.pi4home.services;


import com.mollin.yapi.YeelightDevice;
import com.mollin.yapi.exception.YeelightResultErrorException;
import com.mollin.yapi.exception.YeelightSocketException;
import com.pi4home.model.yeelight.Yeelight;
import com.pi4home.model.yeelight.YeelightDeviceWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class YeelightService
{
    @Autowired
    YeelightDeviceWrapper deskYeelight;
    @Autowired
    YeelightDeviceWrapper rightYeelight;
    @Autowired
    YeelightDeviceWrapper leftYeelight;

    @Autowired
    List<YeelightDeviceWrapper> yeelightDeviceWrappers = Arrays.asList(deskYeelight, rightYeelight, leftYeelight);

    public void updateYeelightStatus(Yeelight yeelight)
    {
        YeelightDeviceWrapper yeelightWrapperByName = getYeelightWrapperByName(yeelight.getName());
        YeelightDevice yeelightDevice = yeelightWrapperByName.getYeelightDevice();
        try
        {
            yeelightDevice.setPower(yeelight.isTurnedOn());
        }
        catch (YeelightResultErrorException e)
        {
            e.printStackTrace();
        }
        catch (YeelightSocketException e)
        {
            e.printStackTrace();
        }
    }


    private YeelightDeviceWrapper getYeelightWrapperByName(String name)
    {
        return yeelightDeviceWrappers
                .stream()
                .filter(yeelightDeviceWrapper -> yeelightDeviceWrapper.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException());
    }
}
