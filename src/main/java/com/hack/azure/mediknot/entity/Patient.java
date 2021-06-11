package com.hack.azure.mediknot.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Patient extends User{
    private List<MedicalEvent> timeline;

}
