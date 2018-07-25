package com.pi4home.configurations;

import com.pi4home.blinds.Blind;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FactoryBeanAppConfig
{
    //ToDo: create wraper for RaspiPin to get a pin usage, if it is set in some other object, shoud throw an exception
    GpioController gpioController = GpioFactory.getInstance();

    @Autowired
    Blind blind1;
    @Autowired
    Blind blind2;
    @Autowired
    Blind blind3;
    @Autowired
    Blind blind4;
    @Autowired
    Blind blind5;

    @Bean(name = "blindLargeWindowLeft")
    public Blind blindFactoryLargeWindowLeft()
    {
        blind1.setName("largeWindowLeft");
        blind1.setGoUpPin(gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_01, "largeWindowLeftUp", PinState.LOW));
        blind1.setGoDownPin(gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_02, "largeWindowLeftDown", PinState.LOW));

        return blind1;
    }

    @Bean(name = "blindLargeWindowRight")
    public Blind blindFactoryLargeWindowRight()
    {
        blind2.setName("largeWindowRight");
        blind2.setGoUpPin(gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_03, "largeWindowRightUp", PinState.LOW));
        blind2.setGoDownPin(gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_04, "largeWindowRightDown", PinState.LOW));

        return blind2;
    }

    @Bean(name = "blindSmallWindowLeft")
    public Blind blindFactorySmallWindowLeft()
    {
        blind3.setName("smallWindowLeft");
        blind3.setGoUpPin(gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_05, "smallWindowLeftUp", PinState.LOW));
        blind3.setGoDownPin(gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_06, "smallWindowLeftDown", PinState.LOW));

        return blind3;
    }

    @Bean(name = "blindSmallWindowMiddle")
    public Blind blindFactorySmallWindowMiddle()
    {
        blind4.setName("smallWindowMiddle");
        blind4.setGoUpPin(gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_07, "smallWindowMiddleUp", PinState.LOW));
        blind4.setGoDownPin(gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_08, "smallWindowMiddleDown", PinState.LOW));

        return blind4;
    }

    @Bean(name = "blindSmallWindowRight")
    public Blind blindFactorySmallWindowRight()
    {
        blind5.setName("smallWindowRight");
        blind5.setGoUpPin(gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_09, "smallWindowRightUp", PinState.LOW));
        blind5.setGoDownPin(gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_10, "smallWindowRightDown", PinState.LOW));

        return blind5;
    }
}
