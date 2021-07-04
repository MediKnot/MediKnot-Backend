package com.hack.azure.mediknot.mapper;


import com.hack.azure.mediknot.dto.DosageDto;
import com.hack.azure.mediknot.entity.Dosage;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring", uses = MedicineMapper.class)
public interface DosageMapper {

    Dosage toEntity(DosageDto dosageDto);

    DosageDto toDto(Dosage dosage);

}