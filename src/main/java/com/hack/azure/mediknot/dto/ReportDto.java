package com.hack.azure.mediknot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hack.azure.mediknot.entity.Address;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReportDto {
    private String name;
    private LocalDate date;

    private Address lab;

    private String reportUrl;

}
