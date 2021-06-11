package com.hack.azure.mediknot.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Medicine {

    enum MedicineType{
        TABLETS, SYRUP, INJECTION, CAPSULE
    }

    enum QuantityType{
        NUMBER, MILLILITRE
    }

    private Integer id;
    private String medicineName;
    private String strength;
    private MedicineType type;
    private Integer quantity;
    private QuantityType quantityType;
}
