package com.hack.azure.mediknot.mapper;


import com.hack.azure.mediknot.dto.DiseaseDto;
import com.hack.azure.mediknot.entity.Disease;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring" )
public interface DiseaseMapper {

    Disease toEntity(DiseaseDto diseaseDto);

    DiseaseDto toDto(Disease disease);

}