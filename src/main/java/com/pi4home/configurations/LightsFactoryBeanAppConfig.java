package com.pi4home.configurations;

import com.pi4home.model.lights.Light;
import com.pi4j.io.gpio.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LightsFactoryBeanAppConfig
{
    private GpioController gpioController = GpioFactory.getInstance();

    @Bean(name = "entranceLight")
    public Light entranceLight()
    {
        Light light = new Light();
        light.setName("entranceLight");
        light.setTurnedOn(false);

        GpioPinDigitalOutput lightOnPin = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_00, "entranceLightOnPin", PinState.LOW);

        light.setLightOnPin(lightOnPin);

        lightOnPin.setShutdownOptions(false, PinState.LOW);

        return light;
    }

    @Bean(name = "sidewalkLight")
    public Light sidewalkLight()
    {
        Light light = new Light();
        light.setName("sidewalkLight");
        light.setTurnedOn(false);

        GpioPinDigitalOutput lightOnPin = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_01, "sidewalkLightOnPin", PinState.LOW);

        light.setLightOnPin(lightOnPin);

        lightOnPin.setShutdownOptions(false, PinState.LOW);

        return light;
    }

}
