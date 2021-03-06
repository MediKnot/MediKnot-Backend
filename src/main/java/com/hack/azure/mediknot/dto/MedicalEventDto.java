package com.hack.azure.mediknot.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.hack.azure.mediknot.entity.Address;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MedicalEventDto {

    private Integer id;

    private PatientDto patientDto;

    private LocalDate startDate;
    private LocalDate endDate;

    private Address location;

    private Boolean isActive;
    private String eventName;
    private String critical;

    private Set<ConsultationMedicalEventDto> consultationList;

    private Set<DiseaseDto> diseases;

    private List<ReportDto> reports;

    private String description;
}
