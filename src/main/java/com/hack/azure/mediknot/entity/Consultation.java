package com.hack.azure.mediknot.entity;

import java.util.List;

public class Consultation {
    private Integer id;
    private Doctor doctor;
    private List<Prescription> prescriptionList;
    private List<Treatment> treatmentList;
}
