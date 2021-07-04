package com.hack.azure.mediknot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Disease {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String introduction;

    @Column(columnDefinition = "TEXT")
    @ElementCollection
    private List<String> symptoms;

    @Column(columnDefinition = "TEXT")
    @ElementCollection
    private List<String> causes;

    @Column(columnDefinition = "TEXT")
    @ElementCollection
    private List<String> diagnosis;

    @Column(columnDefinition = "TEXT")
    @ElementCollection
    private List<String> cures;
}
