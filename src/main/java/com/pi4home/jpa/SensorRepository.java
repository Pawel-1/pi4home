package com.pi4home.jpa;

import com.pi4home.restResources.Sensor;
import com.pi4home.restResources.TaskValue;
import org.springframework.data.repository.CrudRepository;

public interface SensorRepository extends CrudRepository<TaskValue, String>
{
}
