package com.fer.sem2.weathermeasurementservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

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
