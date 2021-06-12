package com.hack.azure.mediknot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TreatmentDto {
    private String treatmentName;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
