package com.pi4home.restResources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Sensors
{
    @JsonProperty("Sensors")
    private List<Sensor> sensors;

    public Sensors()
    {

    }

    public List<Sensor> getSensors()
    {
        return sensors;
    }

    public void setSensors(List<Sensor> sensors)
    {
        this.sensors = sensors;
    }

    @Override
    public String toString()
    {
        return "Sensors{" +
                "sensors=" + sensors +
                '}';
    }
}
