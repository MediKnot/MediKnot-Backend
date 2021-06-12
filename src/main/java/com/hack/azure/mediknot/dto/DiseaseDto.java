package com.hack.azure.mediknot.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DiseaseDto {
    private Integer id;

    private String name;
    private String introduction;
    private List<String> symptoms;
    private List<String> causes;
    private List<String> diagnosis;
    private List<String> cures;
}
