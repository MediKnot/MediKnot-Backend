package com.hack.azure.mediknot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DosageDto {

    private MedicineDto medicine;
    private String reason;
    private Double amount;
    private String frequency;
    private String duration;
}
