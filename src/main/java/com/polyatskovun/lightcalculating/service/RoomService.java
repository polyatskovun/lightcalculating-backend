package com.polyatskovun.lightcalculating.service;

import com.polyatskovun.lightcalculating.domain.Room;
import com.polyatskovun.lightcalculating.domain.enums.RecordTypeEnum;
import com.polyatskovun.lightcalculating.dto.RecordDto;
import com.polyatskovun.lightcalculating.dto.RoomDto;
import com.polyatskovun.lightcalculating.mapper.RoomMapper;
import com.polyatskovun.lightcalculating.repository.RoomRepository;
import com.polyatskovun.lightcalculating.repository.RoomTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoomService {

    private final RoomRepository repository;
    private final RoomMapper mapper;
    private final RoomTypeRepository roomTypeRepository;
    private final RecordService recordService;

    public RoomService(RoomRepository repository, RoomMapper mapper, RoomTypeRepository roomTypeRepository, RecordService recordService) {
        this.repository = repository;
        this.mapper = mapper;
        this.roomTypeRepository = roomTypeRepository;
        this.recordService = recordService;
    }

    public RoomDto save(RoomDto dto) {
        Room room = mapper.toEntity(dto);
        if (room.getRoomType() != null && room.getRoomType().getId() != null) {
            roomTypeRepository.findById(room.getRoomType().getId()).ifPresent(room::setRoomType);
        }
        RoomDto roomDto = mapper.toDto(repository.save(room));
        roomDto.setYearCount(dto.getYearCount());
        roomDto.setRecords(dto.getRecords());
        if (roomDto.getRecords() != null && !roomDto.getRecords().isEmpty()) {
            roomDto.getRecords().stream().filter(r -> r.getRecordType().getId() == RecordTypeEnum.EXISTING.getId()).findFirst().ifPresent(r -> {
                r.setRoom(roomDto);
                recordService.saveExisting(r);
            });
        }
        recordService.saveForRoom(roomDto);
        roomDto.getRecords().forEach(r -> r.getRoom().setRecords(null));
        return roomDto;
    }

    public List<RoomDto> findAll() {
        List<RoomDto> res = repository.findAll().stream().map(mapper::toDto).toList();
        List<RecordDto> records = recordService.findAll();
        res.forEach(r -> r.setRecords(records.stream().filter(recordModel -> recordModel.getRoom().getId().equals(r.getId())).toList()));
        return res;
    }

    public void deleteById(Long id) {
        recordService.deleteAllByRoomId(id);
        repository.deleteById(id);
    }

    public RoomDto findById(Long id) {
        RoomDto roomDto = repository.findById(id).map(mapper::toDto).orElseThrow(RuntimeException::new);
        roomDto.setRecords(recordService.findByRoomId(roomDto.getId()));
        return roomDto;
    }
}
