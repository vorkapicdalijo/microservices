package com.fer.sem2.weathermeasurementservice.controller;


import com.fer.sem2.weathermeasurementservice.dto.WeatherMeasurementRequest;
import com.fer.sem2.weathermeasurementservice.service.WeatherMeasurementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather-measurement")
@RequiredArgsConstructor
public class WeatherMeasurementController {

    private final WeatherMeasurementService weatherMeasurementService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String addWeatherMeasurement(@RequestBody WeatherMeasurementRequest weatherMeasurementRequest) {
        return weatherMeasurementService.addWeatherMeasurement(weatherMeasurementRequest);
    }
}
