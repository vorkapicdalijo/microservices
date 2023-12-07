package com.fer.sem2.locationservice.controller;

import com.fer.sem2.locationservice.dto.LocationRequest;
import com.fer.sem2.locationservice.model.Location;
import com.fer.sem2.locationservice.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/location")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String addLocation(@RequestBody LocationRequest locationRequest) {

        return locationService.addLocation(locationRequest);
    }
    @GetMapping
    public List<Location> getAllLocations() {
        return locationService.getAllLocations();
    }
}
