package com.fer.sem2.dataprocessingservice.dto;

import com.fer.sem2.dataprocessingservice.event.WeatherMeasurementEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessedWeatherRequest {

    private String locationName;
    private Integer locationId;
    private String weatherPrediction;
    private LocalDate predictionOccurrence;
    private double predictionAccuracy;
    private LocalDate createdAt;
}
