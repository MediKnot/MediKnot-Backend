package com.hack.azure.mediknot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hack.azure.mediknot.entity.Dosage;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PrescriptionDto {
    private Integer id;
    private LocalDate date;
    private String prescriptionUrl;
    private List<Dosage> dosageList;
}
