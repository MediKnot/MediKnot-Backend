package com.hack.azure.mediknot.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Prescription {
    private Date date;
    private List<Dosage> dosageList;
}
