package com.fer.sem2.weathermeasurementservice.service;

import com.fer.sem2.weathermeasurementservice.dto.WeatherMeasurementRequest;
import com.fer.sem2.weathermeasurementservice.event.WeatherMeasurementEvent;
import com.fer.sem2.weathermeasurementservice.model.WeatherMeasurement;
import com.fer.sem2.weathermeasurementservice.repository.WeatherMeasurementRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherMeasurementService {

    private final WeatherMeasurementRepository weatherMeasurementRepository;
    private final KafkaTemplate<String, WeatherMeasurementEvent> kafkaTemplate;

    public String addWeatherMeasurement(WeatherMeasurementRequest weatherMeasurementRequest) {
        log.info("New Weather Measurement added!");

        WeatherMeasurement weatherMeasurement = new WeatherMeasurement();

        weatherMeasurement.setType(weatherMeasurementRequest.getType());
        weatherMeasurement.setValue(weatherMeasurementRequest.getValue());
        weatherMeasurement.setMeasuringUnit(weatherMeasurementRequest.getMeasuringUnit());
        weatherMeasurement.setDescription(weatherMeasurementRequest.getDescription());
        weatherMeasurement.setLocationName(weatherMeasurementRequest.getLocationName());
        weatherMeasurement.setCreatedAt(LocalDate.now());

        WeatherMeasurement savedWeatherMeasurement = weatherMeasurementRepository.save(weatherMeasurement);

        WeatherMeasurementEvent weatherMeasurementEvent = new WeatherMeasurementEvent();

        weatherMeasurement.setId(savedWeatherMeasurement.getId());
        weatherMeasurementEvent.setType(savedWeatherMeasurement.getType());
        weatherMeasurementEvent.setValue(savedWeatherMeasurement.getValue());
        weatherMeasurementEvent.setMeasuringUnit(savedWeatherMeasurement.getMeasuringUnit());
        weatherMeasurementEvent.setDescription(savedWeatherMeasurement.getDescription());
        weatherMeasurementEvent.setLocationName(savedWeatherMeasurement.getLocationName());
        weatherMeasurementEvent.setCreatedAt(LocalDate.now());

        log.info("Sending Raw {} Weather Measurement of value: {} {} to Data Processing Service.",weatherMeasurement.getType(), weatherMeasurement.getValue(), weatherMeasurement.getMeasuringUnit());
        kafkaTemplate.send("measurementTopic", weatherMeasurementEvent);
        return "New Weather Measurement added!";
    }
}
