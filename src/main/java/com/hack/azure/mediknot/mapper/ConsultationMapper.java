package com.hack.azure.mediknot.mapper;


import com.hack.azure.mediknot.dto.ConsultationDto;
import com.hack.azure.mediknot.dto.DoctorDto;
import com.hack.azure.mediknot.dto.PatientDto;
import com.hack.azure.mediknot.dto.PrescriptionDto;
import com.hack.azure.mediknot.entity.Consultation;
import com.hack.azure.mediknot.entity.Doctor;
import com.hack.azure.mediknot.entity.Patient;
import com.hack.azure.mediknot.entity.Prescription;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring", uses = {TreatmentMapper.class, PrescriptionMapper.class})
public interface ConsultationMapper {

    Consultation toEntity(ConsultationDto consultationDto);

    ConsultationDto toDto(Consultation consultation);

    default DoctorDto doctorToDto(Doctor doctor){
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setId(doctor.getId());
        doctorDto.setName(doctor.getName());
        return doctorDto;
    }

//    default PrescriptionDto prescriptionToDto(Prescription prescription){
//        PrescriptionDto prescriptionDto = new PrescriptionDto();
//        prescriptionDto.setId(prescription.getId());
//        prescriptionDto.setDate(prescription.getDate());
//        return prescriptionDto;
//    }

    default PatientDto patientToDto(Patient patient){
        if(patient == null)
            return null;
        PatientDto patientDto = new PatientDto();
        patientDto.setId(patient.getId());
        patientDto.setName(patient.getName());
        return patientDto;
    }

}