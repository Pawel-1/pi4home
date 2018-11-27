package com.pi4home.restResources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sensor
{
    @Id
    @JsonProperty("TaskName")
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "sensor_name")
    @JsonProperty("TaskValues")
    private List<TaskValue> taskValues;

    public Sensor()
    {

    }

    public List<TaskValue> getTaskValues()
    {
        return taskValues;
    }

    public void setTaskValues(List<TaskValue> taskValues)
    {
        this.taskValues = taskValues;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "Sensor{" +
                "taskValues=" + taskValues +
                ", name='" + name + '\'' +
                '}';
    }
}
