package com.hack.azure.mediknot.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ConsultationMedicalEventDto {
    private Integer id;
    private DoctorDto doctor;
    private LocalDate consultationDate;
    private List<String> concerns;
    private List<String> notes;
}
