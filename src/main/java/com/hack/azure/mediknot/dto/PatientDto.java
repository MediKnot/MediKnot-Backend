package com.hack.azure.mediknot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hack.azure.mediknot.entity.HealthFields;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientDto extends UserDto {

    private String bloodGroup;
    private Float weight;
    private Float height;

    private List<HealthFields> heartRate;

    private List<HealthFields> bloodPressure;

    private List<HealthFields> sugarLevel;

    private List<HealthFields> haemoglobin;

    private List<MedicalEventDto> timeline;
    private List<String> allergies;
    private List<ReportDto> generalReports;

    private String meetingLink;

}
