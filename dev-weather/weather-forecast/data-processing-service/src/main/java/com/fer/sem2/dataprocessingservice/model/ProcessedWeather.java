package com.fer.sem2.dataprocessingservice.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Table(name="data_processing")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProcessedWeather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String locationName;
    private String weatherPrediction;
    private LocalDate predictionOccurrence;
    private double predictionAccuracy;
    private LocalDate createdAt;
    private double temperature;
    private String temperatureUnit;
    private double wind;
    private String windUnit;
    private String airQuality;
    private double humidity;
    private String humidityUnit;
}
