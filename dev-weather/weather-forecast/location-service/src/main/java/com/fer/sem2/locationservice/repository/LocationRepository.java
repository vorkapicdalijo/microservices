package com.fer.sem2.locationservice.repository;

import com.fer.sem2.locationservice.model.Location;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LocationRepository extends MongoRepository<Location, String> {
}
