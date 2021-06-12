package com.hack.azure.mediknot.dto;


import com.hack.azure.mediknot.entity.Address;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embedded;
import java.time.LocalDate;

@Getter
@Setter
public class MedicalDto {

    private Integer id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Address location;
    private Boolean isActive;

}
