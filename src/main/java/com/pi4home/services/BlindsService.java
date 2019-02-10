package com.pi4home.services;

import com.pi4home.jpa.BlindStateRepository;
import com.pi4home.model.blinds.Blind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BlindsService
{
    @Autowired
    private BlindStateRepository blindStateRepository;
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

        double percentageMaskingState = blind.getPercentageMaskingState();

        if (percentageMaskingState == 100)
        {
            blind.setMasking(0);
        }
        else if (percentageMaskingState == 0)
        {
            blind.setMasking(100);
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

    public void updateBlindState(Blind blindRq) throws InterruptedException
    {
        Blind blindByName = getBlindByName(blindRq.getName());
        double blindStateRq = blindRq.getPercentageMaskingState();
        blindByName.setMasking(blindStateRq);
        blindStateRepository.save(blindByName);
    }


    public void initDb()
    {
        blindList
                .forEach(blind -> blindStateRepository.save(blind));

    }
}
