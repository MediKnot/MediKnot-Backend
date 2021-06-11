package com.hack.azure.mediknot.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Doctor extends User{
    private List<String> specialization;	
    private List<String> degree;
    private List<String> medicalCouncilAffilation;
    private List<String> clinicName;
    private String registrationNumber;
    private Integer rating;
    private Integer yearOfExperience;
}
