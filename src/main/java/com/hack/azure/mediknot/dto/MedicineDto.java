package com.hack.azure.mediknot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MedicineDto {
    private Integer id;

    private String medicineName;
    private String strength;

    private String medicineFullName;

    private String type;

    private Float mrp;
    private String category;
    private String uses;
    private String composition;
    private String manufacturer;
    private String sideEffect;

    private Integer quantity;
    private String medicineQuantityType;
}
