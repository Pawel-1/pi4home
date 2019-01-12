package com.pi4home.configurations;

import com.pi4home.model.lights.Light;
import com.pi4j.io.gpio.*;
import org.springframework.context.annotation.Bean;

public class LightsFactoryBeanAppConfig
{
    private GpioController gpioController = GpioFactory.getInstance();

    @Bean(name = "entranceLight")
    public Light entranceLight()
    {
        Light light = new Light();
        light.setName("entrance");
        light.setTurnedOn(false);

        GpioPinDigitalOutput lightOnPin = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_14, "entranceLightOnPin", PinState.LOW);

        light.setLightOnPin(lightOnPin);

        lightOnPin.setShutdownOptions(false, PinState.LOW);

        return light;
    }

}
