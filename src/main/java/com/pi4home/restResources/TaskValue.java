package com.pi4home.restResources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    @Transient
    private Date date = new Date();
    @Transient
    String strDateFormat = "hh:mm:ss a";
    @Transient
    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);

    String formattedDate= dateFormat. format(date);

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
