package com.fer.sem2.forecastservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessedWeatherResponse {

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
