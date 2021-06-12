package com.hack.azure.mediknot.mapper;


import com.hack.azure.mediknot.dto.DosageDto;
import com.hack.azure.mediknot.entity.Dosage;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring" )
public interface DosageMapper {

    Dosage toEntity(DosageDto dosageDto);

    DosageDto toDto(Dosage dosage);

}