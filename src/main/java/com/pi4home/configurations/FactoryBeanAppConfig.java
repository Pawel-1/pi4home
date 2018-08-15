package com.pi4home.configurations;

import com.pi4home.blinds.Blind;
import com.pi4j.io.gpio.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FactoryBeanAppConfig
{
    GpioController gpioController = GpioFactory.getInstance();


    @Bean(name = "blindLargeWindowLeft")
    public Blind blindFactoryLargeWindowLeft()
    {
        Blind blind1 = new Blind();
        blind1.setName("largeWindowLeft");
        GpioPinDigitalOutput largeWindowLeftUpPin = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_14, "largeWindowLeftUp", PinState.LOW);
        GpioPinDigitalOutput largeWindowLeftDownPin = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_02, "largeWindowLeftDown", PinState.LOW);

        blind1.setGoUpPin(largeWindowLeftUpPin);
        blind1.setGoDownPin(largeWindowLeftDownPin);

        largeWindowLeftUpPin.setShutdownOptions(false, PinState.LOW);
        largeWindowLeftDownPin.setShutdownOptions(false, PinState.LOW);

        return blind1;
    }

    @Bean(name = "blindLargeWindowRight")
    public Blind blindFactoryLargeWindowRight()
    {
        Blind blind2 = new Blind();
        blind2.setName("largeWindowRight");
        GpioPinDigitalOutput largeWindowRightUpPin = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_03, "largeWindowRightUp", PinState.LOW);
        GpioPinDigitalOutput largeWindowRightDownPin = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_04, "largeWindowRightDown", PinState.LOW);

        blind2.setGoUpPin(largeWindowRightUpPin);
        blind2.setGoDownPin(largeWindowRightDownPin);

        largeWindowRightUpPin.setShutdownOptions(false, PinState.LOW);
        largeWindowRightDownPin.setShutdownOptions(false, PinState.LOW);

        return blind2;
    }

    @Bean(name = "blindSmallWindowLeft")
    public Blind blindFactorySmallWindowLeft()
    {
        Blind blind3 = new Blind();

        blind3.setName("smallWindowLeft");
        GpioPinDigitalOutput smallWindowLeftUpPin = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_21, "smallWindowLeftUp", PinState.LOW);
        GpioPinDigitalOutput smallWindowLeftDownPin = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_22, "smallWindowLeftDown", PinState.LOW);

        blind3.setGoUpPin(smallWindowLeftUpPin);
        blind3.setGoDownPin(smallWindowLeftDownPin);

        smallWindowLeftUpPin.setShutdownOptions(false, PinState.LOW);
        smallWindowLeftDownPin.setShutdownOptions(false, PinState.LOW);

        return blind3;
    }

    @Bean(name = "blindSmallWindowMiddle")
    public Blind blindFactorySmallWindowMiddle()
    {
        Blind blind4 = new Blind();

        blind4.setName("smallWindowMiddle");
        GpioPinDigitalOutput smallWindowMiddleUpPin = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_23, "smallWindowMiddleUp", PinState.LOW);
        GpioPinDigitalOutput smallWindowMiddleDownPin = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_24, "smallWindowMiddleDown", PinState.LOW);

        blind4.setGoUpPin(smallWindowMiddleUpPin);
        blind4.setGoDownPin(smallWindowMiddleDownPin);

        smallWindowMiddleUpPin.setShutdownOptions(false, PinState.LOW);
        smallWindowMiddleDownPin.setShutdownOptions(false, PinState.LOW);

        return blind4;
    }

    @Bean(name = "blindSmallWindowRight")
    public Blind blindFactorySmallWindowRight()
    {
        Blind blind5 = new Blind();

        blind5.setName("smallWindowRight");
        GpioPinDigitalOutput smallWindowRightUpPin = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_28, "smallWindowRightUp", PinState.LOW);
        GpioPinDigitalOutput smallWindowRightDownPin = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_29, "smallWindowRightDown", PinState.LOW);

        blind5.setGoUpPin(smallWindowRightUpPin);
        blind5.setGoDownPin(smallWindowRightDownPin);

        smallWindowRightUpPin.setShutdownOptions(false, PinState.LOW);
        smallWindowRightDownPin.setShutdownOptions(false, PinState.LOW);

        return blind5;
    }
}
