package com.fer.sem2.weathermeasurementservice.repository;

import com.fer.sem2.weathermeasurementservice.model.WeatherMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherMeasurementRepository extends JpaRepository<WeatherMeasurement, Long> {
}
