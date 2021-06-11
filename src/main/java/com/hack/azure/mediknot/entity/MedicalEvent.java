package com.hack.azure.mediknot.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class MedicalEvent {

    private Integer id;
    private Patient patient;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isActive;
    private List<Consultation> consultationList;
    //precautions
}
