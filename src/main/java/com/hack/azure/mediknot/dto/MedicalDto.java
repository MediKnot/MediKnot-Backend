package com.hack.azure.mediknot.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.hack.azure.mediknot.entity.Address;
import com.hack.azure.mediknot.enums.MedicalEventType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embedded;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MedicalDto {

    private Integer id;

    private PatientDto patientDto;

    private LocalDate startDate;
    private LocalDate endDate;
    private Address location;
    private Boolean isActive;
    private MedicalEventType critical;

}
