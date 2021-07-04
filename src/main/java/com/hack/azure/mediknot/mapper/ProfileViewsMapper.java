package com.hack.azure.mediknot.mapper;

import com.hack.azure.mediknot.dto.ProfileViewsDto;
import com.hack.azure.mediknot.entity.ProfileViews;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring" )
public interface ProfileViewsMapper {

    ProfileViews toEntity(ProfileViewsDto profileViewsDto);

    ProfileViewsDto toDto(ProfileViews profileViews);

}
