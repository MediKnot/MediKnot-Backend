package com.hack.azure.mediknot.mapper;

import com.hack.azure.mediknot.dto.ReportDto;
import com.hack.azure.mediknot.entity.Report;
import org.mapstruct.Mapper;


@Mapper( componentModel = "spring" )
public interface ReportMapper {
    Report toEntity(ReportDto reportDto);

    ReportDto toDto(Report report);
}
