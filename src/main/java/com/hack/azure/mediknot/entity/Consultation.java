package com.hack.azure.mediknot.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Consultation {
    private Integer id;
    private Doctor doctor;
    private List<Prescription> prescriptionList;
    private List<Treatment> treatmentList;
}
