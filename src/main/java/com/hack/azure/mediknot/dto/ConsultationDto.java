package com.hack.azure.mediknot.dto;

import com.hack.azure.mediknot.entity.Doctor;
import com.hack.azure.mediknot.entity.Prescription;
import com.hack.azure.mediknot.entity.Treatment;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class ConsultationDto {
    private Integer id;
    private Doctor doctor;

    private Set<Prescription> prescriptionList;

    private List<Treatment> treatmentList;

    private List<String> notes;

    private LocalDate consultationDate;
}
