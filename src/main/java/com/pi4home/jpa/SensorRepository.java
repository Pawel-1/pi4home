package com.pi4home.jpa;

import com.pi4home.restResources.Sensor;
import org.springframework.data.repository.CrudRepository;

public interface SensorRepository extends CrudRepository<Sensor, String>
{
}
