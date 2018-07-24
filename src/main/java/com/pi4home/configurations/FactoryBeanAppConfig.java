package com.pi4home.configurations;

import com.pi4home.model.Blind;
import com.pi4home.providers.BlindsFactory;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FactoryBeanAppConfig
{
    //ToDo: create wraper for RaspiPin to get a pin usage, if it is set in some other object, shoud throw an exception
    GpioController gpioController = GpioFactory.getInstance();

    @Bean(name = "blindLargeWindowLeft")
    public BlindsFactory blindFactoryLargeWindowLeft()
    {
        BlindsFactory blindsFactory = new BlindsFactory();
        blindsFactory.setName("largeWindowLeft");
        blindsFactory.setGoUpPin(gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_01, "largeWindowLeftUp", PinState.LOW));
        blindsFactory.setGoDownPin(gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_02, "largeWindowLeftDown", PinState.LOW));

        return blindsFactory;
    }

    @Bean
    public Blind blindLargeWindowLeft() throws Exception
    {
        return blindFactoryLargeWindowLeft().getObject();
    }

    @Bean(name = "blindLargeWindowRight")
    public BlindsFactory blindFactoryLargeWindowRight()
    {
        BlindsFactory blindsFactory = new BlindsFactory();
        blindsFactory.setName("largeWindowRight");
        blindsFactory.setGoUpPin(gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_03, "largeWindowRightUp", PinState.LOW));
        blindsFactory.setGoDownPin(gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_04, "largeWindowRightDown", PinState.LOW));

        return blindsFactory;
    }

    @Bean
    public Blind blindLargeWindowRight() throws Exception
    {
        return blindFactoryLargeWindowRight().getObject();
    }

    @Bean(name = "blindSmallWindowLeft")
    public BlindsFactory blindFactorySmallWindowLeft()
    {
        BlindsFactory blindsFactory = new BlindsFactory();
        blindsFactory.setName("smallWindowLeft");
        blindsFactory.setGoUpPin(gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_05, "smallWindowLeftUp", PinState.LOW));
        blindsFactory.setGoDownPin(gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_06, "smallWindowLeftDown", PinState.LOW));

        return blindsFactory;
    }

    @Bean
    public Blind blindSmallWindowLeft() throws Exception
    {
        return blindFactorySmallWindowLeft().getObject();
    }

    @Bean(name = "blindSmallWindowMiddle")
    public BlindsFactory blindFactorySmallWindowMiddle()
    {
        BlindsFactory blindsFactory = new BlindsFactory();
        blindsFactory.setName("smallWindowMiddle");
        blindsFactory.setGoUpPin(gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_07, "smallWindowMiddleUp", PinState.LOW));
        blindsFactory.setGoDownPin(gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_08, "smallWindowMiddleDown", PinState.LOW));

        return blindsFactory;
    }

    @Bean
    public Blind blindSmallWindowMiddle() throws Exception
    {
        return blindFactorySmallWindowMiddle().getObject();
    }

    @Bean(name = "blindSmallWindowRight")
    public BlindsFactory blindFactorySmallWindowRight()
    {
        BlindsFactory blindsFactory = new BlindsFactory();
        blindsFactory.setName("smallWindowRight");
        blindsFactory.setGoUpPin(gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_09, "smallWindowRightUp", PinState.LOW));
        blindsFactory.setGoDownPin(gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_10, "smallWindowRightDown", PinState.LOW));

        return blindsFactory;
    }

    @Bean
    public Blind blindSmallWindowRight() throws Exception
    {
        return blindFactorySmallWindowRight().getObject();
    }
}
