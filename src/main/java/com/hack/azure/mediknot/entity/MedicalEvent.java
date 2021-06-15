package com.hack.azure.mediknot.entity;

import com.hack.azure.mediknot.enums.MedicalEventType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class MedicalEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private Patient patient;

    private LocalDate startDate;
    private LocalDate endDate;

    @Embedded
    private Address location;

    private Boolean isActive;

    @Enumerated(EnumType.STRING)
    private MedicalEventType critical;

    @OneToMany
    private Set<Consultation> consultationList;

    @OneToMany
    private Set<Disease> diseases;

    @ElementCollection
    private List<Report> reports;
    //precautions
}
