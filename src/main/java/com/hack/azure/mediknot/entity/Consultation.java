package com.hack.azure.mediknot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    private Doctor doctor;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Prescription> prescriptionList;

    @ElementCollection
    @OrderBy("startTime ASC")
    private List<Treatment> treatmentList;

    @ElementCollection
    private List<String> notes;

    @ManyToOne
    private Patient patient;

    private LocalDate consultationDate;

    @ElementCollection
    private List<String> concerns;
}
