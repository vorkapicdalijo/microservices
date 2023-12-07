package com.fer.sem2.dataprocessingservice.repository;

import com.fer.sem2.dataprocessingservice.model.ProcessedWeather;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DataProcessingRepository extends JpaRepository<ProcessedWeather, Long> {
    List<ProcessedWeather> findByLocationName(String locationName);
}
