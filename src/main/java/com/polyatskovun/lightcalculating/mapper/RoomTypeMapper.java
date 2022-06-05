package com.polyatskovun.lightcalculating.mapper;

import com.polyatskovun.lightcalculating.domain.RoomType;
import com.polyatskovun.lightcalculating.dto.RoomTypeDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomTypeMapper {

    RoomType toEntity(RoomTypeDto dto);

    RoomTypeDto toDto(RoomType entity);
}
