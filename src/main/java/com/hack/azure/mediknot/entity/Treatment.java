package com.hack.azure.mediknot.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Treatment {
    private String treatmentName;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
