package com.pi4home.configurations;

import com.pi4home.jpa.BlindStateRepository;
import com.pi4home.model.blinds.Blind;
import com.pi4j.io.gpio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.NoSuchElementException;
import java.util.Optional;

@Configuration
public class BlindsFactoryBeanAppConfig
{
    @Autowired
    BlindStateRepository blindStateRepository;
    private GpioController gpioController = GpioFactory.getInstance();

    @Bean(name = "blindLargeWindowLeft")
    public Blind blindFactoryLargeWindowLeft()
    {
        Blind blind = new Blind();
        blind.setName("largeWindowLeft");
        blind.setPercentageMaskingState(getBlindStateFromDB(blind));

        GpioPinDigitalOutput largeWindowLeftUpPin = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_02, "largeWindowLeftUp", PinState.LOW);
        GpioPinDigitalOutput largeWindowLeftDownPin = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_03, "largeWindowLeftDown", PinState.LOW);

        blind.setGoUpPin(largeWindowLeftUpPin);
        blind.setGoDownPin(largeWindowLeftDownPin);

        largeWindowLeftUpPin.setShutdownOptions(false, PinState.LOW);
        largeWindowLeftDownPin.setShutdownOptions(false, PinState.LOW);

        return blind;
    }

    @Bean(name = "blindLargeWindowRight")
    public Blind blindFactoryLargeWindowRight()
    {
        Blind blind = new Blind();
        blind.setName("largeWindowRight");
        blind.setPercentageMaskingState(getBlindStateFromDB(blind));

        GpioPinDigitalOutput largeWindowRightUpPin = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_05, "largeWindowRightUp", PinState.LOW);
        GpioPinDigitalOutput largeWindowRightDownPin = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_04, "largeWindowRightDown", PinState.LOW);

        blind.setGoUpPin(largeWindowRightUpPin);
        blind.setGoDownPin(largeWindowRightDownPin);

        largeWindowRightUpPin.setShutdownOptions(false, PinState.LOW);
        largeWindowRightDownPin.setShutdownOptions(false, PinState.LOW);

        return blind;
    }

    @Bean(name = "blindSmallWindowRight")
    public Blind blindFactorySmallWindowLeft()
    {
        Blind blind = new Blind();
        blind.setName("smallWindowRight");
        blind.setPercentageMaskingState(getBlindStateFromDB(blind));

        GpioPinDigitalOutput smallWindowLeftUpPin = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_21, "smallWindowLeftUp", PinState.LOW);
        GpioPinDigitalOutput smallWindowLeftDownPin = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_22, "smallWindowLeftDown", PinState.LOW);

        blind.setGoUpPin(smallWindowLeftUpPin);
        blind.setGoDownPin(smallWindowLeftDownPin);

        smallWindowLeftUpPin.setShutdownOptions(false, PinState.LOW);
        smallWindowLeftDownPin.setShutdownOptions(false, PinState.LOW);

        return blind;
    }

    @Bean(name = "blindSmallWindowLeft")
    public Blind blindFactorySmallWindowMiddle()
    {
        Blind blind = new Blind();
        blind.setName("smallWindowLeft");
        blind.setPercentageMaskingState(getBlindStateFromDB(blind));

        GpioPinDigitalOutput smallWindowMiddleUpPin = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_28, "smallWindowMiddleUp", PinState.LOW);
        GpioPinDigitalOutput smallWindowMiddleDownPin = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_29, "smallWindowMiddleDown", PinState.LOW);

        blind.setGoUpPin(smallWindowMiddleUpPin);
        blind.setGoDownPin(smallWindowMiddleDownPin);

        smallWindowMiddleUpPin.setShutdownOptions(false, PinState.LOW);
        smallWindowMiddleDownPin.setShutdownOptions(false, PinState.LOW);

        return blind;
    }

    @Bean(name = "blindSmallWindowMiddle")
    public Blind blindFactorySmallWindowRight()
    {
        Blind blind = new Blind();
        blind.setName("smallWindowMiddle");
        blind.setPercentageMaskingState(getBlindStateFromDB(blind));

        GpioPinDigitalOutput smallWindowRightUpPin = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_24, "smallWindowRightUp", PinState.LOW);
        GpioPinDigitalOutput smallWindowRightDownPin = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_23, "smallWindowRightDown", PinState.LOW);

        blind.setGoUpPin(smallWindowRightUpPin);
        blind.setGoDownPin(smallWindowRightDownPin);

        smallWindowRightUpPin.setShutdownOptions(false, PinState.LOW);
        smallWindowRightDownPin.setShutdownOptions(false, PinState.LOW);

        return blind;
    }

    private double getBlindStateFromDB(Blind blind)
    {
        Optional<Blind> blindFromDb = blindStateRepository
                .findById(blind.getName());

        if (blindFromDb.isPresent())
        {
            return blindFromDb.get().getPercentageMaskingState();
        }
        return 0;
    }
}
