package com.hack.azure.mediknot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Patient extends User{

    private String bloodGroup;
    private Float weight;
    private Float height;

    @ElementCollection
    private List<HealthFields> heartRate;

    @ElementCollection
    private List<HealthFields> bloodPressure;

    @ElementCollection
    private List<HealthFields> sugarLevel;

    @ElementCollection
    private List<HealthFields> haemoglobin;


    @OneToMany(mappedBy = "patient")
    private List<MedicalEvent> timeline;

    @ElementCollection
    private List<String> allergies;

    @ElementCollection
    private List<Report> generalReports;

    private String meetingLink;

}
