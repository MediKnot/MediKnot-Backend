package com.hack.azure.mediknot.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Disease {
    private Integer id;
    private String name;
    private String introduction;
    private List<String> symptoms;
    private List<String> causes;
    private List<String> diagnosis;
    private List<String> cures;
}
