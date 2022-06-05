package com.polyatskovun.lightcalculating.service;

import com.polyatskovun.lightcalculating.domain.Lamp;
import com.polyatskovun.lightcalculating.dto.LampDto;
import com.polyatskovun.lightcalculating.mapper.LampMapper;
import com.polyatskovun.lightcalculating.repository.LampRepository;
import com.polyatskovun.lightcalculating.repository.LampTypeRepository;
import com.polyatskovun.lightcalculating.repository.SocleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LampService {

    private final LampRepository repository;
    private final LampMapper mapper;
    private final LampTypeRepository lampTypeRepository;
    private final SocleRepository socleRepository;

    public LampService(LampRepository repository, LampMapper mapper, LampTypeRepository lampTypeRepository, SocleRepository socleRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.lampTypeRepository = lampTypeRepository;
        this.socleRepository = socleRepository;
    }

    public LampDto save(LampDto dto) {
        Lamp lamp = mapper.toEntity(dto);
        if (lamp.getLampType() != null && lamp.getLampType().getId() != null) {
            lampTypeRepository.findById(lamp.getLampType().getId()).ifPresent(lamp::setLampType);
        }
        if (lamp.getSocle() != null && lamp.getSocle().getId() != null) {
            socleRepository.findById(lamp.getSocle().getId()).ifPresent(lamp::setSocle);
        }
        return mapper.toDto(repository.save(lamp));
    }

    public List<LampDto> findAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
