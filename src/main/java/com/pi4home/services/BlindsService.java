package com.pi4home.services;

import com.pi4home.blinds.Blind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static com.pi4home.blinds.BlindState.covered;
import static com.pi4home.blinds.BlindState.uncovered;

@Service
public class BlindsService
{
    @Autowired
    private Blind blindLargeWindowLeft;

    @Autowired
    private Blind blindLargeWindowRight;

    @Autowired
    private Blind blindSmallWindowLeft;

    @Autowired
    private Blind blindSmallWindowMiddle;

    @Autowired
    private Blind blindSmallWindowRight;

    @Autowired
    List<Blind> blindList = Arrays.asList(
            blindLargeWindowLeft,
            blindLargeWindowRight,
            blindSmallWindowLeft,
            blindSmallWindowMiddle,
            blindSmallWindowRight);

    public void toggleBlindState(String blindName) throws InterruptedException
    {
        Blind blind = getBlindByName(blindName);
        int percentageMaskingState = blind.getBlindState().getPercentageMaskingState();

        if (percentageMaskingState == 100)
        {
            blind.setMasking(uncovered());
        }
        else if (percentageMaskingState == 0)
        {
            blind.setMasking(covered());
        }
    }

    public Blind getBlindByName(String pinName)
    {
        return blindList
                .stream()
                .filter(blind -> blind.getName().equals(pinName))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException());
    }

    public List<Blind> getBlindList()
    {
        return blindList;
    }

    public void updateBlindState(Blind blind) throws InterruptedException
    {
        String name = blind.getName();
        Blind blindByName = getBlindByName(name);
        blindByName.setMasking(blindByName.getBlindState());
    }
}
