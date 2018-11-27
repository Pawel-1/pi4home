package com.pi4home.restResources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskValue
{
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Value")
    private double value;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;

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
