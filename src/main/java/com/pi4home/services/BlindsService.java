package com.pi4home.services;

import com.pi4home.jpa.BlindStateRepository;
import com.pi4home.model.blinds.Blind;
import com.pi4home.model.blinds.BlindState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static com.pi4home.model.blinds.BlindState.covered;
import static com.pi4home.model.blinds.BlindState.uncovered;

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

        int percentageMaskingState = blind.getBlindState().getPercentageMaskingState();

        if (percentageMaskingState == 100)
        {
            blind.setMasking(uncovered(blindName));
        }
        else if (percentageMaskingState == 0)
        {
            blind.setMasking(covered(blindName));
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
        BlindState blindStateRq = blindRq.getBlindState();
        blindByName.setMasking(blindStateRq);
    }


    public void initDb()
    {
//        blindList
//                .forEach(blind -> blind.setBlindState(uncovered(blind.getName())));

        blindList
                .forEach(blind -> blindStateRepository.save(uncovered(blind.getName())));

    }

//    private BlindState getBlindState()
//    {
//        return blindStateRepository
//                .findById(this.getName())
//                .orElseThrow(() -> new NoSuchElementException());
//    }
//
//    private void setBlindState(BlindState blindState)
//    {
//        blindStateRepository.save(blindState);
//    }
}
