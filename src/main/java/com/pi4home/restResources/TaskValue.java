package com.pi4home.restResources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskValue
{
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Value")
    private double value;

    public TaskValue()
    {

    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getValue()
    {
        return value;
    }

    public void setValue(double value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return "TaskValue{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
