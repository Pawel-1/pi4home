package com.pi4home.providers;

import com.pi4home.enums.BlindState;
import com.pi4home.model.Blind;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class BlindsFactory implements FactoryBean<Blind>
{
    GpioPinDigitalOutput goUpPin;
    GpioPinDigitalOutput goDownPin;
    String name;

    @Override
    public Blind getObject() throws Exception
    {
        Blind blind = new Blind();
        blind.setBlindState(BlindState.UP);
        blind.setName(name);
        blind.setGoUpPin(goUpPin);
        blind.setGoDownPin(goDownPin);
        return blind;
    }

    @Override
    public Class<?> getObjectType()
    {
        return null;
    }

    @Override
    public boolean isSingleton()
    {
        return false;
    }

    public GpioPinDigitalOutput getGoUpPin()
    {
        return goUpPin;
    }

    public void setGoUpPin(GpioPinDigitalOutput goUpPin)
    {
        this.goUpPin = goUpPin;
    }

    public GpioPinDigitalOutput getGoDownPin()
    {
        return goDownPin;
    }

    public void setGoDownPin(GpioPinDigitalOutput goDownPin)
    {
        this.goDownPin = goDownPin;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
