package com.fer.sem2.dataprocessingservice.controller;

import com.fer.sem2.dataprocessingservice.dto.ProcessedWeatherRequest;
import com.fer.sem2.dataprocessingservice.model.ProcessedWeather;
import com.fer.sem2.dataprocessingservice.service.DataProcessingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/data-processing")
@RequiredArgsConstructor
public class DataProcessingController {

    private final DataProcessingService dataProcessingService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addData(@RequestBody ProcessedWeatherRequest processedWeatherRequest) {
        dataProcessingService.storeProcessedData(processedWeatherRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProcessedWeather> getProcessedWeather(@RequestParam String locationName) {
        return dataProcessingService.getProcessedWeather(locationName);
    }
}
