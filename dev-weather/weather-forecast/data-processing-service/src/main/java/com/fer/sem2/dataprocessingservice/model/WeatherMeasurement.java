package com.fer.sem2.dataprocessingservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="weather_measurement")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeatherMeasurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private double value;
    private String measuringUnit;
    private String description;
    private String locationName;
    private LocalDate createdAt;

}
