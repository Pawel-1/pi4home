package com.pi4home;

import com.pi4home.services.BlindsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application
{

    @Autowired
    private static BlindsService blindsService;

    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
        blindsService.setUpBlindService();
    }

}
