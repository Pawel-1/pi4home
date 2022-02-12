package com.pi4home.model.lights;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LIGHTS")
public class Light
{
    @Id
    private String name;
    boolean isTurnedOn;

    public void setName(String name)
    {
        this.name = name;
    }

    public void setTurnedOn(boolean turnedOn)
    {
        isTurnedOn = turnedOn;
    }

    public boolean isTurnedOn()
    {
        return isTurnedOn;
    }

    public String getName()
    {
        return name;
    }
}
