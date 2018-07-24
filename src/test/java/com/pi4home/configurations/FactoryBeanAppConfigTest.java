package com.pi4home.configurations;

import com.pi4home.model.Blind;
import com.pi4home.providers.BlindsFactory;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = FactoryBeanAppConfig.class)
public class FactoryBeanAppConfigTest
{

    @Resource
    private Blind blind;

    @Resource(name = "&blindLargeWindowLeft")
    private BlindsFactory blindsFactory;

    @Test
    public void testFactoryReturnsCorrectBean()
    {
        assertThat(blind.getName(), equalTo("blindLargeWindowLeft"));
        assertThat(blind.getGoUpPin().getPin(), equalTo(RaspiPin.GPIO_01));
        assertThat(blind.getGoUpPin().getName(), equalTo("largeWindowLeftUp"));
        assertThat(blind.getGoUpPin().getState(), equalTo(PinState.LOW));

        assertThat(blind.getGoDownPin().getPin(), equalTo(RaspiPin.GPIO_02));
        assertThat(blind.getGoUpPin().getName(), equalTo("largeWindowLeftDown"));
        assertThat(blind.getGoUpPin().getState(), equalTo(PinState.LOW));
    }
}