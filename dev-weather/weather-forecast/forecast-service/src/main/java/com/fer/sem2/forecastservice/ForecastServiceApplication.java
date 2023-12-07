package com.fer.sem2.forecastservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ForecastServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForecastServiceApplication.class, args);
    }

}
