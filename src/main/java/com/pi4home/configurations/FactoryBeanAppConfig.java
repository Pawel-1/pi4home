package com.pi4home.configurations;

import com.pi4home.blinds.Blind;
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
    public Blind blindFactoryLargeWindowLeft()
    {
        Blind blind1 = new Blind();
        blind1.setName("largeWindowLeft");
        blind1.setGoUpPin(gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_14, "largeWindowLeftUp", PinState.LOW));
        blind1.setGoDownPin(gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_02, "largeWindowLeftDown", PinState.LOW));

        return blind1;
    }

    @Bean(name = "blindLargeWindowRight")
    public Blind blindFactoryLargeWindowRight()
    {
        Blind blind2 = new Blind();
        blind2.setName("largeWindowRight");
        blind2.setGoUpPin(gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_03, "largeWindowRightUp", PinState.LOW));
        blind2.setGoDownPin(gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_04, "largeWindowRightDown", PinState.LOW));

        return blind2;
    }

    @Bean(name = "blindSmallWindowLeft")
    public Blind blindFactorySmallWindowLeft()
    {
        Blind blind3 = new Blind();

        blind3.setName("smallWindowLeft");
        blind3.setGoUpPin(gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_21, "smallWindowLeftUp", PinState.LOW));
        blind3.setGoDownPin(gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_22, "smallWindowLeftDown", PinState.LOW));

        return blind3;
    }

    @Bean(name = "blindSmallWindowMiddle")
    public Blind blindFactorySmallWindowMiddle()
    {
        Blind blind4 = new Blind();

        blind4.setName("smallWindowMiddle");
        blind4.setGoUpPin(gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_23, "smallWindowMiddleUp", PinState.LOW));
        blind4.setGoDownPin(gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_24, "smallWindowMiddleDown", PinState.LOW));

        return blind4;
    }

    @Bean(name = "blindSmallWindowRight")
    public Blind blindFactorySmallWindowRight()
    {
        Blind blind5 = new Blind();

        blind5.setName("smallWindowRight");
        blind5.setGoUpPin(gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_07, "smallWindowRightUp", PinState.LOW));
        blind5.setGoDownPin(gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_08, "smallWindowRightDown", PinState.LOW));

        return blind5;
    }
}
