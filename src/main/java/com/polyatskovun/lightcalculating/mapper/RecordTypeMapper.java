package com.polyatskovun.lightcalculating.mapper;

import com.polyatskovun.lightcalculating.domain.RecordType;
import com.polyatskovun.lightcalculating.dto.RecordTypeDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecordTypeMapper {

    RecordType toEntity(RecordTypeDto dto);

    RecordTypeDto toDto(RecordType entity);
}
