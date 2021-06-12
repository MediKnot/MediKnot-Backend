package com.hack.azure.mediknot.mapper;


import com.hack.azure.mediknot.dto.TreatmentDto;
import com.hack.azure.mediknot.entity.Treatment;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring" )
public interface TreatmentMapper {

    Treatment toEntity(TreatmentDto treatmentDto);

    TreatmentDto toDto(Treatment treatment);

}