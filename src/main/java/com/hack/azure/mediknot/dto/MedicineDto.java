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

    private String type;

    private Integer quantity;
    private String medicineQuantityType;
}
