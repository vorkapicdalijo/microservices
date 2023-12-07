package com.fer.sem2.forecastservice.model;

import com.fer.sem2.forecastservice.dto.ProcessedWeatherResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Forecast {
    private String title;
    private String description;
    private List<ProcessedWeatherResponse> processedWeatherResponses;
}
