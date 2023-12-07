package com.fer.sem2.locationservice.service;

import com.fer.sem2.locationservice.dto.LocationRequest;
import com.fer.sem2.locationservice.model.Location;
import com.fer.sem2.locationservice.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LocationService {

    private final LocationRepository locationRepository;

    public String addLocation(LocationRequest locationRequest) {
        Location location = new Location();

        location.setName(locationRequest.getName());
        location.setRegion(locationRequest.getRegion());
        location.setCountry(locationRequest.getCountry());

        locationRepository.save(location);

        return "Location added successfully!";
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

}
