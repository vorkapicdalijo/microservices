package com.fer.sem2.dataprocessingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherMeasurementRequest {
    private String type;
    private double value;
    private String measuringUnit;
    private String description;
    private String locationName;
    private LocalDate createdAt;
}
