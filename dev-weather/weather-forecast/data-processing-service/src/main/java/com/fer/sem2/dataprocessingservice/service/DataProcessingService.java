package com.fer.sem2.dataprocessingservice.service;

import com.fer.sem2.dataprocessingservice.dto.ProcessedWeatherRequest;
import com.fer.sem2.dataprocessingservice.event.WeatherMeasurementEvent;
import com.fer.sem2.dataprocessingservice.model.ProcessedWeather;
import com.fer.sem2.dataprocessingservice.model.WeatherMeasurement;
import com.fer.sem2.dataprocessingservice.repository.DataProcessingRepository;
import com.fer.sem2.dataprocessingservice.repository.WeatherMeasurementRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class DataProcessingService {

    private final DataProcessingRepository dataProcessingRepository;
    private final WeatherMeasurementRepository weatherMeasurementRepository;

    @KafkaListener(topics = "measurementTopic")
    public String getWeatherMeasurement(WeatherMeasurementEvent weatherMeasurementEvent) {
        log.info("Received Weather Measurement from Weather Measurement Service!");
        log.info("{}: {} {}, measured on date: {}, for location: {}", weatherMeasurementEvent.getType(), weatherMeasurementEvent.getValue(), weatherMeasurementEvent.getMeasuringUnit(), weatherMeasurementEvent.getCreatedAt(), weatherMeasurementEvent.getLocationName());

        WeatherMeasurement weatherMeasurement = new WeatherMeasurement();

        weatherMeasurement.setType(weatherMeasurementEvent.getType());
        weatherMeasurement.setMeasuringUnit(weatherMeasurementEvent.getMeasuringUnit());
        weatherMeasurement.setValue(weatherMeasurementEvent.getValue());
        weatherMeasurement.setDescription(weatherMeasurementEvent.getDescription());
        weatherMeasurement.setLocationName(weatherMeasurementEvent.getLocationName());
        weatherMeasurement.setId(weatherMeasurementEvent.getId());
        weatherMeasurement.setCreatedAt(weatherMeasurementEvent.getCreatedAt());

        return processWeatherMeasurement(weatherMeasurement.getLocationName(), weatherMeasurement.getCreatedAt());

    }

    @Transactional(readOnly = true)
    @SneakyThrows
    public List<ProcessedWeather> getProcessedWeather(String location) {
        return dataProcessingRepository.findByLocationName(location);
    }

    public void storeProcessedData(ProcessedWeatherRequest dataProcessingRequest) {

        ProcessedWeather processedWeather = new ProcessedWeather();

        processedWeather.setCreatedAt(LocalDate.now());
        processedWeather.setLocationName(dataProcessingRequest.getLocationName());
        processedWeather.setPredictionAccuracy(dataProcessingRequest.getPredictionAccuracy());
        processedWeather.setPredictionOccurrence(dataProcessingRequest.getPredictionOccurrence());
        processedWeather.setWeatherPrediction(dataProcessingRequest.getWeatherPrediction());

        dataProcessingRepository.save(processedWeather);
        processWeatherMeasurement("Zagreb", LocalDate.of(2023, 5, 25));
    }

    private String processWeatherMeasurement(String locationName, LocalDate createdAt) {

        ProcessedWeather processedWeather = new ProcessedWeather();

        List<WeatherMeasurement> weatherMeasurements = weatherMeasurementRepository.findByLocationName(locationName);

        log.info("Processing Weather Measurements...");
        for (WeatherMeasurement measurement: weatherMeasurements) {
            log.info("          {}: {} {}, created at: {}, for location: {}", measurement.getType(), measurement.getValue(), measurement.getMeasuringUnit(), measurement.getCreatedAt(), measurement.getLocationName());
        }
        log.info("Making weather predictions....");

        Random random = new Random();

        List<String> weatherConditions = List.of("Sunny", "Rainy", "Cloudy", "Snowy", "Blizzard", "Storm", "Thunderstorm", "Wind storm", "Drought");
        List<String> airQualities = List.of("Excellent","Good", "Moderate", "Unhealthy", "Bad");

        int randomIndexWeather = random.nextInt(weatherConditions.size());
        int randomIndexAir = random.nextInt(airQualities.size());

        double temperature = 0.0 + random.nextDouble()*(35.0);
        double humidity = 0.0 + random.nextDouble() * 100.0;
        double wind = 5.0 + random.nextDouble() * 80.0;
        String condition = weatherConditions.get(randomIndexWeather);
        String airQuality = airQualities.get(randomIndexAir);
        double accuracy = 0.0 + random.nextDouble()*0.95;
        int plusDays = random.nextInt(9) + 2;

        processedWeather.setCreatedAt(LocalDate.now());
        processedWeather.setLocationName(locationName);
        processedWeather.setPredictionAccuracy(accuracy);
        processedWeather.setPredictionOccurrence(LocalDate.now().plusDays(plusDays));
        processedWeather.setWeatherPrediction(condition);
        processedWeather.setAirQuality(airQuality);
        processedWeather.setTemperature(temperature);
        processedWeather.setTemperatureUnit("degree Celsius");
        processedWeather.setHumidity(humidity);
        processedWeather.setHumidityUnit("percent");
        processedWeather.setWind(wind);
        processedWeather.setWindUnit("kilometers per hour");

        dataProcessingRepository.save(processedWeather);

        String res = "New Weather Prediction for location "+locationName+" created";

        return res;

    }
}
