package com.pi4home.jpa;

import com.pi4home.model.blinds.Blind;
import org.springframework.data.repository.CrudRepository;

public interface BlindRepository extends CrudRepository<Blind, String>
{

}
