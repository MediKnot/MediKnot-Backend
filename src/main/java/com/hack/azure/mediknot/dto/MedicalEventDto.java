package com.hack.azure.mediknot.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.hack.azure.mediknot.entity.Address;
import com.hack.azure.mediknot.entity.Consultation;
import com.hack.azure.mediknot.entity.Patient;
import com.hack.azure.mediknot.entity.Report;
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
    private Patient patient;

    private LocalDate startDate;
    private LocalDate endDate;

    private Address location;

    private Boolean isActive;

    private String critical;

    private Set<Consultation> consultationList;

    private List<Report> reports;

}
