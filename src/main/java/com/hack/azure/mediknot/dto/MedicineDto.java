package com.hack.azure.mediknot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicineDto {
    private Integer id;

    private String medicineName;
    private String strength;

    private String type;

    private Integer quantity;
    private String medicineQuantityType;
}
