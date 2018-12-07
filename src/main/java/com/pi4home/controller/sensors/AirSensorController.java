package com.pi4home.controller.sensors;

import com.pi4home.jpa.SensorRepository;
import com.pi4home.restResources.Sensors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class AirSensorController
{
    @Value("${espEasy.url}")
    String url;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    SensorRepository sensorRepository;

    @RequestMapping("/airSensor")
    public Sensors readFromAirSensors()
    {
        Sensors sensors = restTemplate.getForObject(url, Sensors.class);

        sensors.getSensors()
                .forEach(sensorRepository::save);

        return sensors;
    }

}
