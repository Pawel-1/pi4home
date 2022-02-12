package com.pi4home.jpa;

import com.pi4home.model.lights.Light;
import org.springframework.data.repository.CrudRepository;

public interface LightRepository extends CrudRepository<Light, String>
{
}
