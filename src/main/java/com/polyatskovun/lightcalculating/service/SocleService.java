package com.polyatskovun.lightcalculating.service;

import com.polyatskovun.lightcalculating.dto.SocleDto;
import com.polyatskovun.lightcalculating.mapper.SocleMapper;
import com.polyatskovun.lightcalculating.repository.SocleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SocleService {

    private final SocleRepository repository;
    private final SocleMapper mapper;

    public SocleService(SocleRepository repository, SocleMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public SocleDto save(SocleDto dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public List<SocleDto> findAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
