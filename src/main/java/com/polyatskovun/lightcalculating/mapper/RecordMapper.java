package com.polyatskovun.lightcalculating.mapper;

import com.polyatskovun.lightcalculating.domain.Record;
import com.polyatskovun.lightcalculating.dto.RecordDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {LampMapper.class, RoomMapper.class, RecordTypeMapper.class})
public interface RecordMapper {

    Record toEntity(RecordDto dto);

    RecordDto toDto(Record entity);
}
