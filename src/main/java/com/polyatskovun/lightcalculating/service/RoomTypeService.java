package com.polyatskovun.lightcalculating.service;

import com.polyatskovun.lightcalculating.dto.RoomTypeDto;
import com.polyatskovun.lightcalculating.mapper.RoomTypeMapper;
import com.polyatskovun.lightcalculating.repository.RoomTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoomTypeService {

    private final RoomTypeRepository repository;
    private final RoomTypeMapper mapper;

    public RoomTypeService(RoomTypeRepository repository, RoomTypeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public RoomTypeDto save(RoomTypeDto dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public List<RoomTypeDto> findAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
