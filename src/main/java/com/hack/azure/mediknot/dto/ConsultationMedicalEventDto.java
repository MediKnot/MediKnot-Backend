package com.hack.azure.mediknot.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ConsultationMedicalEventDto {
    private Integer id;
    private String doctorName;
    private LocalDate consultationDate;
}
