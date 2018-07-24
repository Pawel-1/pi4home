package com.pi4home.services;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class BlindsServiceTest
{
    @Autowired
    BlindsService blindsService;

    @Test
    public void getPinByNameReturnsPin() throws Exception
    {
        blindsService.toggleBlindState("pinBlindLargeWindowLeft");
    }

}