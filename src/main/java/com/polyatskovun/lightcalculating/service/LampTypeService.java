package com.polyatskovun.lightcalculating.service;

import com.polyatskovun.lightcalculating.dto.LampTypeDto;
import com.polyatskovun.lightcalculating.mapper.LampTypeMapper;
import com.polyatskovun.lightcalculating.repository.LampTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LampTypeService {

    private final LampTypeRepository repository;
    private final LampTypeMapper mapper;

    public LampTypeService(LampTypeRepository repository, LampTypeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<LampTypeDto> findAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

}
