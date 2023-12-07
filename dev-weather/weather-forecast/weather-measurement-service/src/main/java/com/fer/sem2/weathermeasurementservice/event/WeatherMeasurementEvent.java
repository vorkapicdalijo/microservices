package com.fer.sem2.weathermeasurementservice.event;

import com.fasterxml.jackson.databind.DatabindException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherMeasurementEvent {
    private Long id;
    private String type;
    private double value;
    private String measuringUnit;
    private String description;
    private String locationName;
    private LocalDate createdAt;
}
