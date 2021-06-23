package com.hack.azure.mediknot.mapper;


import com.hack.azure.mediknot.dto.ConsultationDto;
import com.hack.azure.mediknot.dto.MedicalEventDto;
import com.hack.azure.mediknot.dto.PatientDto;
import com.hack.azure.mediknot.entity.Consultation;
import com.hack.azure.mediknot.entity.MedicalEvent;
import com.hack.azure.mediknot.entity.Patient;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring", uses = ReportMapper.class)
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
        medicalEventDto.setDescription(medicalEvent.getDescription());
        return medicalEventDto;
    }

}