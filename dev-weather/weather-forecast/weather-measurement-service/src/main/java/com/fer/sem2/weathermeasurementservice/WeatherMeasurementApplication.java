package com.fer.sem2.weathermeasurementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class WeatherMeasurementApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherMeasurementApplication.class, args);
    }
}
