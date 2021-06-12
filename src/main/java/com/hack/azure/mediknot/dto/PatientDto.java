package com.hack.azure.mediknot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientDto extends UserDto {

    private List<MedicalEventDto> timeline;
    private List<String> allergies;
    private List<ReportDto> generalReports;

}
