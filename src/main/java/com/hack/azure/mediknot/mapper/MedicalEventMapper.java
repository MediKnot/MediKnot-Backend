package com.hack.azure.mediknot.mapper;


import com.hack.azure.mediknot.dto.MedicalEventDto;
import com.hack.azure.mediknot.entity.MedicalEvent;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring" )
public interface MedicalEventMapper {

    MedicalEvent toEntity(MedicalEventDto medicalEventDto);

    MedicalEventDto toDto(MedicalEvent medicalEvent);

}