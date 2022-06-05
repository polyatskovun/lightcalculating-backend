package com.polyatskovun.lightcalculating.mapper;

import com.polyatskovun.lightcalculating.domain.User;
import com.polyatskovun.lightcalculating.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDto dto);

    UserDto toDto(User entity);
}
