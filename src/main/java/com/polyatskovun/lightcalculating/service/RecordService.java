package com.polyatskovun.lightcalculating.service;

import com.polyatskovun.lightcalculating.domain.Record;
import com.polyatskovun.lightcalculating.domain.enums.RecordTypeEnum;
import com.polyatskovun.lightcalculating.dto.LampDto;
import com.polyatskovun.lightcalculating.dto.RecordDto;
import com.polyatskovun.lightcalculating.dto.RoomDto;
import com.polyatskovun.lightcalculating.mapper.RecordMapper;
import com.polyatskovun.lightcalculating.mapper.RecordTypeMapper;
import com.polyatskovun.lightcalculating.repository.LampRepository;
import com.polyatskovun.lightcalculating.repository.RecordRepository;
import com.polyatskovun.lightcalculating.repository.RecordTypeRepository;
import com.polyatskovun.lightcalculating.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RecordService {

    private final RecordRepository repository;
    private final RecordMapper mapper;
    private final RoomRepository roomRepository;
    private final RecordTypeRepository recordTypeRepository;
    private final RecordTypeMapper recordTypeMapper;
    private final LampRepository lampRepository;
    private final LampService lampService;

    public RecordService(RecordRepository repository, RecordMapper mapper, RoomRepository roomRepository, RecordTypeMapper recordTypeMapper, LampService lampService, RecordTypeRepository recordTypeRepository, LampRepository lampRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.roomRepository = roomRepository;
        this.recordTypeMapper = recordTypeMapper;
        this.lampService = lampService;
        this.recordTypeRepository = recordTypeRepository;
        this.lampRepository = lampRepository;
    }

    public RecordDto save(RecordDto dto) {
        Record record = mapper.toEntity(dto);
        if (record.getRecordType() != null && record.getRecordType().getId() != null) {
            recordTypeRepository.findById(record.getRecordType().getId()).ifPresent(record::setRecordType);
        }
        if (record.getRoom() != null && record.getRoom().getId() != null) {
            roomRepository.findById(record.getRoom().getId()).ifPresent(record::setRoom);
        }
        if (record.getLamp() != null && record.getLamp().getId() != null) {
            lampRepository.findById(record.getLamp().getId()).ifPresent(record::setLamp);
        }
        return mapper.toDto(repository.save(record));
    }

    public List<RecordDto> findAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public List<RecordDto> findByRoomId(Long roomId) {
        return repository.findAllByRoomId(roomId).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public void saveForRoom(RoomDto room) {
        lampService.findAll().forEach(lamp -> {
            RecordDto recordDto = new RecordDto();
            Integer countLamp = getCountLamp(room, lamp);
            Integer countSocle = getCountSocle(lamp, countLamp);
            double lampsOnAllYears = lamp.getPrice() * (countLamp * room.getYearCount() * room.getHoursOfUses() * 365) / lamp.getTermOfWork();
            double electricEnergy = (lamp.getPower() * room.getYearCount() * room.getHoursOfUses() * 365 * 1.66) / 1000;
            recordDto.setCountLamp(countLamp);
            recordDto.setCountSocle(countSocle);
            recordDto.setSum(countSocle * lamp.getSocle().getPrice() + lampsOnAllYears + countLamp * electricEnergy);
            recordDto.setYearCount(room.getYearCount());
            recordDto.setRoom(room);
            recordDto.setLamp(lamp);
            recordTypeRepository.findById(RecordTypeEnum.NEW.getId()).map(recordTypeMapper::toDto).ifPresent(recordDto::setRecordType);
            save(recordDto);
        });
    }

    private Integer getCountSocle(LampDto lamp, Integer countLamp) {
        return Double.valueOf(Math.ceil((double) countLamp / lamp.getSocle().getPlace())).intValue();
    }

    private Integer getCountLamp(RoomDto room, LampDto lamp) {
        double ceil = Math.ceil(((double) (room.getSquare() * lamp.getLuminousFlux())) / room.getRoomType().getLightningRate());
        return Double.valueOf(ceil).intValue();
    }
}
