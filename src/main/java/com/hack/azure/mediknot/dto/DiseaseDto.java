package com.hack.azure.mediknot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DiseaseDto {
    private Integer id;

    private String name;
    private String introduction;
    private List<String> symptoms;
    private List<String> causes;
    private List<String> diagnosis;
    private List<String> cures;
}
