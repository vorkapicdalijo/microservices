package com.fer.sem2.forecastservice.service;

import com.fer.sem2.forecastservice.dto.ForecastRequest;
import com.fer.sem2.forecastservice.dto.ProcessedWeatherResponse;
import com.fer.sem2.forecastservice.event.NotificationEvent;
import com.fer.sem2.forecastservice.model.Forecast;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ForecastService {

    private final WebClient.Builder webClientBuilder;
    private final KafkaTemplate<String, NotificationEvent> kafkaTemplate;

    public Forecast getForecast(ForecastRequest forecastRequest) {

        log.info("Fetching Processed Weather for Forecast, for location: {}",forecastRequest.getLocationName());
        log.info("Contacting Data Processing Service...");

        ProcessedWeatherResponse[] processedWeatherResponses = webClientBuilder.build()
                .get().uri("http://data-processing-service/api/data-processing",
                        uriBuilder -> uriBuilder
                                .queryParam("locationName", forecastRequest.getLocationName())
                                .build())
                .retrieve()
                .bodyToMono(ProcessedWeatherResponse[].class)
                .block();

        log.info("Received {} Weather Predictions!", processedWeatherResponses.length);
        log.info("Checking for extreme conditions...");
        for(ProcessedWeatherResponse w: processedWeatherResponses) {
            if(w.getWeatherPrediction().equalsIgnoreCase("Blizzard") || w.getWeatherPrediction().equalsIgnoreCase("Storm")
                    || w.getWeatherPrediction().equalsIgnoreCase("Thunderstorm") || w.getWeatherPrediction().equalsIgnoreCase("Wind storm")
                    || w.getWeatherPrediction().equalsIgnoreCase("Drought")) {
                log.info("Extreme weather condition: {} ! Informing Notification Service", w.getWeatherPrediction());
                kafkaTemplate.send("notificationTopic", new NotificationEvent("Extreme Weather Condition!", w.getWeatherPrediction(), w.getPredictionOccurrence()));
            }
        }

        Forecast forecast = new Forecast();
        forecast.setTitle("Forecasts for location: "+forecastRequest.getLocationName());
        forecast.setDescription("List of separated processed weather predictions");
        forecast.setProcessedWeatherResponses(Arrays.stream(processedWeatherResponses).toList());

        return forecast;
    }
}
