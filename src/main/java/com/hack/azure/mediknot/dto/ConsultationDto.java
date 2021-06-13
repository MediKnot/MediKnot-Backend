package com.hack.azure.mediknot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hack.azure.mediknot.entity.Doctor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConsultationDto {
    private Integer id;
    private DoctorDto doctorDto;

    private Set<PrescriptionDto> prescriptionsDto;

    private List<TreatmentDto> treatmentsDto;

    private List<String> notes;

    private LocalDate consultationDate;
}
