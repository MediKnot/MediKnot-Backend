package com.hack.azure.mediknot.mapper;


import com.hack.azure.mediknot.dto.ConsultationDto;
import com.hack.azure.mediknot.dto.DiseaseDto;
import com.hack.azure.mediknot.dto.MedicalEventDto;
import com.hack.azure.mediknot.dto.PatientDto;
import com.hack.azure.mediknot.entity.Consultation;
import com.hack.azure.mediknot.entity.Disease;
import com.hack.azure.mediknot.entity.MedicalEvent;
import com.hack.azure.mediknot.entity.Patient;
import org.mapstruct.Mapper;

import java.util.HashSet;
import java.util.Set;

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
        if(medicalEvent.getEventName()!=null){
            medicalEventDto.setEventName(medicalEvent.getEventName());
        }
        medicalEventDto.setCritical(medicalEvent.getCritical().toString());
        medicalEventDto.setDescription(medicalEvent.getDescription());
        medicalEventDto.setDiseases(diseaseSetToDiseaseDtoSet(medicalEvent.getDiseases()));
        return medicalEventDto;
    }

    default DiseaseDto diseaseToDto(Disease disease){
        DiseaseDto diseaseDto = new DiseaseDto();
        diseaseDto.setId(disease.getId());
        diseaseDto.setName(disease.getName());
        return diseaseDto;
    }

    default Set<DiseaseDto> diseaseSetToDiseaseDtoSet(Set<Disease> set) {
        if ( set == null ) {
            return null;
        }

        Set<DiseaseDto> set1 = new HashSet<DiseaseDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Disease disease : set ) {
            set1.add( diseaseToDto( disease ) );
        }

        return set1;
    }

}
