package com.hack.azure.mediknot.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.ElementCollection;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DoctorDto extends UserDto {
    private List<String> specialization;
    private List<String> degree;
    private List<String> medicalCouncilAffiliation;
    private List<String> clinicName;
    private String registrationNumber;
    private Integer rating;
    private Integer yearOfExperience;
}
