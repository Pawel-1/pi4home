package com.pi4home.services;

import com.pi4home.blinds.Blind;
import com.pi4home.enums.BlindState;
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
        BlindState blindState = blind.getBlindState();

        if (blindState == BlindState.UP)
        {
            blind.blindGoesDown();
        }
        else
        {
            blind.blindGoesUp();
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
}
