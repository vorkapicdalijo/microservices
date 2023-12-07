package com.fer.sem2.forecastservice.controller;


import com.fer.sem2.forecastservice.dto.ForecastRequest;
import com.fer.sem2.forecastservice.dto.ProcessedWeatherResponse;
import com.fer.sem2.forecastservice.model.Forecast;
import com.fer.sem2.forecastservice.service.ForecastService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/forecast")
@RequiredArgsConstructor
public class ForecastController {

    private final ForecastService forecastService;

    @PostMapping
    @CircuitBreaker(name="forecast", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "forecast")
    @Retry(name = "forecast")
    public CompletableFuture<Forecast> getForecast(@RequestBody ForecastRequest forecastRequest) {
        return CompletableFuture.supplyAsync(() -> forecastService.getForecast(forecastRequest));
    }

    public CompletableFuture<String> fallbackMethod(ForecastRequest forecastRequest, RuntimeException runtimeException){
        return CompletableFuture.supplyAsync(() -> "Something went wrong, please try again later!");
    }
}
