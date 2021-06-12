package com.hack.azure.mediknot.mapper;


import com.hack.azure.mediknot.dto.MedicalEventDto;
import com.hack.azure.mediknot.dto.PatientDto;
import com.hack.azure.mediknot.entity.MedicalEvent;
import com.hack.azure.mediknot.entity.Patient;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring" )
public interface PatientMapper {

    Patient toEntity(PatientDto patientDto);

    PatientDto toDto(Patient patient);

    default MedicalEventDto medicalEventToDto(MedicalEvent medicalEvent){
        MedicalEventDto medicalEventDto = new MedicalEventDto();
        medicalEventDto.setId(medicalEvent.getId());
        medicalEventDto.setStartDate(medicalEvent.getStartDate());
        medicalEventDto.setEndDate(medicalEvent.getEndDate());
        medicalEventDto.setIsActive(medicalEvent.getIsActive());
        medicalEventDto.setCritical(medicalEvent.getCritical().toString());
        return medicalEventDto;
    }

}