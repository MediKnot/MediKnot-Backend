package com.hack.azure.mediknot.mapper;

import com.hack.azure.mediknot.dto.ConsultationMedicalEventDto;
import com.hack.azure.mediknot.dto.DiseaseDto;
import com.hack.azure.mediknot.dto.MedicalEventDto;
import com.hack.azure.mediknot.dto.PatientDto;
import com.hack.azure.mediknot.entity.Consultation;
import com.hack.azure.mediknot.entity.Disease;
import com.hack.azure.mediknot.entity.MedicalEvent;
import com.hack.azure.mediknot.entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper( componentModel = "spring", uses = ReportMapper.class)
public interface MedicalEventMapper {

    MedicalEvent toEntity(MedicalEventDto medicalEventDto);

    @Mapping(source = "medicalEvent.consultationList", target = "consultationList")
    MedicalEventDto toDto(MedicalEvent medicalEvent);

    default PatientDto patientToDto(Patient patient){
        PatientDto patientDto = new PatientDto();
        patientDto.setId(patient.getId());
        patientDto.setName(patient.getName());
        patientDto.setAadharNumber(patient.getAadharNumber());
        return patientDto;
    }

    @Mapping(source = "consultation.id", target = "id")
    @Mapping(source = "consultation.doctor.name", target = "doctorName")
    @Mapping(source = "consultation.consultationDate", target = "consultationDate")
    ConsultationMedicalEventDto consultationToDto(Consultation consultation);

    default DiseaseDto diseaseToDto(Disease disease){
        DiseaseDto diseaseDto = new DiseaseDto();
        diseaseDto.setId(disease.getId());
        diseaseDto.setName(disease.getName());
        return diseaseDto;
    }
}