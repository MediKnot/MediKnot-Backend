package com.hack.azure.mediknot.mapper;


import com.hack.azure.mediknot.dto.DoctorDto;
import com.hack.azure.mediknot.entity.Doctor;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring" )
public interface DoctorMapper {

    Doctor toEntity(DoctorDto doctorDto);

    DoctorDto toDto(Doctor doctor);

}