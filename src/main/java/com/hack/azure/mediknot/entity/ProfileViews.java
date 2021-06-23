package com.hack.azure.mediknot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ProfileViews {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private Patient patient;

    private LocalDateTime timestamp;

    private String viewersName;

    private String viewersEmail;

}
