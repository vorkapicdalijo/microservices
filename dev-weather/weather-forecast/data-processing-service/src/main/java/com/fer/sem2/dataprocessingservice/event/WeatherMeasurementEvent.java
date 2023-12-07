package com.fer.sem2.dataprocessingservice.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
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