package com.hack.azure.mediknot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class Patient extends User{

    @OneToMany(mappedBy = "patient")
    private List<MedicalEvent> timeline;

    @ElementCollection
    private List<String> allergies;

    @ElementCollection
    private List<Report> generalReports;
}
