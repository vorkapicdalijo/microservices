package com.fer.sem2.locationservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value="location")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Location {

    @Id
    private String id;
    private String name;
    private String region;
    private String country;
}
