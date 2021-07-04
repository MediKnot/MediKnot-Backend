package com.hack.azure.mediknot.mapper;

import com.hack.azure.mediknot.dto.*;
import com.hack.azure.mediknot.entity.*;
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
    @Mapping(source = "consultation.doctor", target = "doctor")
    @Mapping(source = "consultation.consultationDate", target = "consultationDate")
    @Mapping(source = "consultation.notes", target = "notes")
    @Mapping(source = "consultation.concerns", target = "concerns")
    ConsultationMedicalEventDto consultationToDto(Consultation consultation);

    default DiseaseDto diseaseToDto(Disease disease){
        DiseaseDto diseaseDto = new DiseaseDto();
        diseaseDto.setId(disease.getId());
        diseaseDto.setName(disease.getName());
        return diseaseDto;
    }

    default DoctorDto doctorToDto(Doctor doctor){
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setId(doctor.getId());
        doctorDto.setName(doctor.getName());
        return doctorDto;
    }
}