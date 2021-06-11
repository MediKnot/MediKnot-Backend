package com.hack.azure.mediknot.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class Consultation {
    private Integer id;
    private Doctor doctor;
    private List<Prescription> prescriptionList;
    private List<Treatment> treatmentList;
    private List<String> notes;
    private LocalDate consultationDate;
}
