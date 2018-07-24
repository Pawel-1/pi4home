package com.pi4home.services;

import com.pi4home.enums.BlindState;
import com.pi4home.model.Blind;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BlindsService
{
    //ToDo: add unit tests
    //ToDo: when application starts, all blinds should go up, it is a default state of Blind object
    private static final long BLIND_MOVEMENT_TIME = 5000;

    @Resource(name = "blindLargeWindowLeft")
    private Blind blindLargeWindowLeft;

    @Resource(name = "blindLargeWindowRight")
    private Blind blindLargeWindowRight;

    @Resource(name = "blindSmallWindowLeft")
    private Blind blindSmallWindowLeft;

    @Resource(name = "blindSmallWindowMiddle")
    private Blind blindSmallWindowMiddle;

    @Resource(name = "blindSmallWindowRight")
    private Blind blindSmallWindowRight;

    //ToDo: create a list of beans
    List<Blind> blindList = Arrays.asList(
            blindLargeWindowLeft,
            blindLargeWindowRight,
            blindSmallWindowLeft,
            blindSmallWindowMiddle,
            blindSmallWindowRight);

    public void toggleBlindState(String pinName) throws InterruptedException
    {
        Blind blind = getBlindByName(pinName);

        if (blind.getBlindState() == BlindState.UP)
        {
            GpioPinDigitalOutput goDownPin = blind.getGoDownPin();
            setHighStateOnPin(goDownPin);
            blind.setBlindState(BlindState.DOWN);
        }
        else if (blind.getBlindState() == BlindState.DOWN)
        {
            GpioPinDigitalOutput goUpPin = blind.getGoUpPin();
            setHighStateOnPin(goUpPin);
            blind.setBlindState(BlindState.UP);
        }
        else
        {
            throw new IllegalStateException("Blind with name " + blind.getName() + " has no BlindState set, cannot perform operation");
        }
    }

    private Blind getBlindByName(String pinName)
    {
        return blindList
                .stream()
                .filter(blind -> blind.getName().equals(pinName))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException());
    }

    private void setHighStateOnPin(GpioPinDigitalOutput digitalPin) throws InterruptedException
    {
        digitalPin.high();
        Thread.sleep(BLIND_MOVEMENT_TIME);
        digitalPin.low();
    }
}
