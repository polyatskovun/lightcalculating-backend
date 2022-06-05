package com.polyatskovun.lightcalculating.mapper;

import com.polyatskovun.lightcalculating.domain.Room;
import com.polyatskovun.lightcalculating.dto.RoomDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {RoomTypeMapper.class})
public interface RoomMapper {

    Room toEntity(RoomDto dto);

    RoomDto toDto(Room entity);
}
