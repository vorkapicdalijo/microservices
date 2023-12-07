package com.fer.sem2.dataprocessingservice.repository;

import com.fer.sem2.dataprocessingservice.model.ProcessedWeather;
import com.fer.sem2.dataprocessingservice.model.WeatherMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface WeatherMeasurementRepository extends JpaRepository<WeatherMeasurement, Long> {

    List<WeatherMeasurement> findByLocationName(String locationName);
}
