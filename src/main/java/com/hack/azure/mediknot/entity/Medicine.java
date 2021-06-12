package com.hack.azure.mediknot.entity;

import com.hack.azure.mediknot.enums.MedicineQuantityType;
import com.hack.azure.mediknot.enums.MedicineType;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String medicineName;
    private String strength;

    @Enumerated(EnumType.STRING)
    private MedicineType type;

    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private MedicineQuantityType medicineQuantityType;
}
