package com.hack.azure.mediknot.dto;

import com.hack.azure.mediknot.entity.Medicine;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DosageDto {

    private Medicine medicine;
    private String reason;
    private Double amount;
    private String frequency;
    private String duration;
}
