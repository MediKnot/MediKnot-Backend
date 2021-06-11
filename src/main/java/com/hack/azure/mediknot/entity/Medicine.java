package com.hack.azure.mediknot.entity;

import com.hack.azure.mediknot.enums.MedicineQuantityType;
import com.hack.azure.mediknot.enums.MedicineType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Medicine {
    private Integer id;
    private String medicineName;
    private String strength;
    private MedicineType type;
    private Integer quantity;
    private MedicineQuantityType medicineQuantityType;
}
