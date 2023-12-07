package com.fer.sem2.notificationservice;


import com.fer.sem2.notificationservice.event.NotificationEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@EnableEurekaClient
@Slf4j
public class NotificationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

    @KafkaListener(topics = "notificationTopic")
    public void handleNotification(NotificationEvent notificationEvent) {
        log.info(notificationEvent.getMessage());
        log.info("A {} will occur on date: {}", notificationEvent.getWeatherCondition(), notificationEvent.getConditionOccurrence());
    }
}
