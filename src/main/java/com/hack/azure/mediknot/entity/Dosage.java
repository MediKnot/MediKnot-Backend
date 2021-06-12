package com.hack.azure.mediknot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Embeddable
@Getter
@Setter
public class Dosage {

    @OneToOne
    private Medicine medicine;

    private String reason;
    private String route;
    private Double amount;
    private String frequency;
    private String duration;
}
