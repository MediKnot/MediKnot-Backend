package com.hack.azure.mediknot.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Doctor extends User{

    @ElementCollection
    private List<String> specialization;

    @ElementCollection
    private List<String> degree;

    @ElementCollection
    private List<String> medicalCouncilAffiliation;

    @ElementCollection
    private List<String> clinicName;

    private String registrationNumber;
    private Integer rating;
    private Integer yearOfExperience;
}
