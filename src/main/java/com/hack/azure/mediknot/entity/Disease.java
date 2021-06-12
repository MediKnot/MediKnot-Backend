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
    private String introduction;

    @ElementCollection
    private List<String> symptoms;

    @ElementCollection
    private List<String> causes;

    @ElementCollection
    private List<String> diagnosis;

    @ElementCollection
    private List<String> cures;
}
