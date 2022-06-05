package com.polyatskovun.lightcalculating.mapper;

import com.polyatskovun.lightcalculating.domain.Socle;
import com.polyatskovun.lightcalculating.dto.SocleDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SocleMapper {

    Socle toEntity(SocleDto dto);

    SocleDto toDto(Socle entity);
}
