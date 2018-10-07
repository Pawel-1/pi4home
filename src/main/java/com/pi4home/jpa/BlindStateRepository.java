package com.pi4home.jpa;

import com.pi4home.model.blinds.BlindState;
import org.springframework.data.repository.CrudRepository;

public interface BlindStateRepository extends CrudRepository<BlindState, String>
{

}
