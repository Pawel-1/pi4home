package com.pi4home.restResources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    private String time = getCurrentDateTime();

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

    private String getCurrentDateTime()
    {
        Date date = new Date();
        String strDateFormat = "hh:mm:ss a";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);

        return dateFormat.format(date);
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
