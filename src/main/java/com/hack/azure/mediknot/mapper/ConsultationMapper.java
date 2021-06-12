package com.hack.azure.mediknot.mapper;


import com.hack.azure.mediknot.dto.ConsultationDto;
import com.hack.azure.mediknot.entity.Consultation;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring" )
public interface ConsultationMapper {

    Consultation toEntity(ConsultationDto consultationDto);

    ConsultationDto toDto(Consultation consultation);

}