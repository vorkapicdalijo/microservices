package com.fer.sem2.dataprocessingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DataProcessingServiceApplication{

    public static void main(String[] args) {
        SpringApplication.run(DataProcessingServiceApplication.class, args);
    }

}
