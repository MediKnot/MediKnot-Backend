package com.hack.azure.mediknot.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dosage {
    private Medicine medicine;
    private String reason;
    private String route;
    private Double amount;
    private String frequency;
    private String duration;
}
