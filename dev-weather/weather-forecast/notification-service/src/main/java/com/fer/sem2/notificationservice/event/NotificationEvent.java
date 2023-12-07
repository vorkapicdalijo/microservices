package com.fer.sem2.notificationservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationEvent {
    private String message;
    private String weatherCondition;
    private LocalDate conditionOccurrence;
}