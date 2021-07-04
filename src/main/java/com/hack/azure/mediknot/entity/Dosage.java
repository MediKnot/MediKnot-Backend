package com.hack.azure.mediknot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Embeddable
@Getter
@Setter
public class Dosage {

    @OneToOne(cascade = CascadeType.ALL)
    private Medicine medicine;

    private String reason;
    private Double amount;
    private String frequency;
    private String duration;
}
