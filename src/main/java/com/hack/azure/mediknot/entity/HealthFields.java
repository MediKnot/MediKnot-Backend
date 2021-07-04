package com.hack.azure.mediknot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Getter
@Setter
@Embeddable
public class HealthFields {
    LocalDate timestamp;
    String healthField;
}
