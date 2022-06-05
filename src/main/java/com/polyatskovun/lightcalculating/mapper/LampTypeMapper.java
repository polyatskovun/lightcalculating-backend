package com.polyatskovun.lightcalculating.mapper;

import com.polyatskovun.lightcalculating.domain.LampType;
import com.polyatskovun.lightcalculating.dto.LampTypeDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LampTypeMapper {

    LampType toEntity(LampTypeDto dto);

    LampTypeDto toDto(LampType entity);
}
