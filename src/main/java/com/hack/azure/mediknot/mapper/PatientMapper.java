package com.hack.azure.mediknot.mapper;


import com.hack.azure.mediknot.dto.PatientDto;
import com.hack.azure.mediknot.entity.Patient;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring" )
public interface PatientMapper {

    Patient toEntity(PatientDto patientDto);

    PatientDto toDto(Patient patient);

}