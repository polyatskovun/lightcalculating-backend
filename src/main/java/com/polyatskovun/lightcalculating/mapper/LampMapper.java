package com.polyatskovun.lightcalculating.mapper;

import com.polyatskovun.lightcalculating.domain.Lamp;
import com.polyatskovun.lightcalculating.dto.LampDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {LampTypeMapper.class, SocleMapper.class})
public interface LampMapper {

    Lamp toEntity(LampDto dto);

    LampDto toDto(Lamp entity);
}
