package com.hack.azure.mediknot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Getter
@Setter
public class Treatment {
    private String treatmentName;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
