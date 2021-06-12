package com.hack.azure.mediknot.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TreatmentDto {
    private String treatmentName;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
