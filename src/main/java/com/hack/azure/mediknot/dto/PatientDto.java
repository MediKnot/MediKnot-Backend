package com.hack.azure.mediknot.dto;

import com.hack.azure.mediknot.entity.MedicalEvent;
import com.hack.azure.mediknot.entity.Report;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PatientDto extends UserDto {

    private List<MedicalEvent> timeline;
    private List<String> allergies;
    private List<Report> generalReports;

}
