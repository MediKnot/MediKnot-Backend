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

    private String medicineFullName;
    private String strength;

    @Enumerated(EnumType.STRING)
    private MedicineType type;

    private Integer quantity;

    private Float mrp;
    private String category;

    @Column(columnDefinition = "TEXT")
    private String uses;

    private String composition;
    private String manufacturer;

    @Column(columnDefinition = "TEXT")
    private String sideEffect;

    @Enumerated(EnumType.STRING)
    private MedicineQuantityType medicineQuantityType;
}
