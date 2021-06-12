package com.hack.azure.mediknot.mapper;

import com.hack.azure.mediknot.dto.UserDto;
import com.hack.azure.mediknot.entity.User;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring" )
public interface UserMapper {

    User userDtoToUser(UserDto userDto);

    UserDto userToUserDto(User user);

}
