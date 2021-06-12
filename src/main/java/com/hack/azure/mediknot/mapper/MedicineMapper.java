package com.hack.azure.mediknot.mapper;


import com.hack.azure.mediknot.dto.MedicineDto;
import com.hack.azure.mediknot.entity.Medicine;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring" )
public interface MedicineMapper {

    Medicine toEntity(MedicineDto medicineDto);

    MedicineDto toDto(Medicine medicine);

}