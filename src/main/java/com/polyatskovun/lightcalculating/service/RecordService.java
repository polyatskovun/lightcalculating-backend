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

import java.util.Comparator;
import java.util.List;

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
        Record recordModel = mapper.toEntity(dto);
        if (recordModel.getRecordType() != null && recordModel.getRecordType().getId() != null) {
            recordTypeRepository.findById(recordModel.getRecordType().getId()).ifPresent(recordModel::setRecordType);
        }
        if (recordModel.getRoom() != null && recordModel.getRoom().getId() != null) {
            roomRepository.findById(recordModel.getRoom().getId()).ifPresent(recordModel::setRoom);
        }
        if (recordModel.getLamp() != null && recordModel.getLamp().getId() != null) {
            lampRepository.findById(recordModel.getLamp().getId()).ifPresent(recordModel::setLamp);
        }
        return mapper.toDto(repository.save(recordModel));
    }

    public List<RecordDto> findAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public List<RecordDto> findByRoomId(Long roomId) {
        return repository.findAllByRoomId(roomId).stream().map(mapper::toDto).toList();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public void saveForRoom(RoomDto room) {
        lampService.findAll().forEach(lamp -> {
            RecordDto recordDto = new RecordDto();
            Integer countLamp = getCountLamp(room, lamp);
            Integer countSocle = getCountSocle(lamp, countLamp);
            setRecordDtoFields(lamp, countLamp, room, recordDto, countSocle, RecordTypeEnum.NEW);
            save(recordDto);
        });
        repository.findAllByRoomId(room.getId()).stream()
                .filter(r -> !r.getRecordType().getId().equals(RecordTypeEnum.EXISTING.getId()))
                .min(Comparator.comparing(Record::getSum))
                .ifPresent(r -> {
                    if (r.getRecordType().getId().equals(RecordTypeEnum.ADDITIONAL.getId())) {
                        recordTypeRepository.findById(RecordTypeEnum.OPTIMAL_ADD.getId()).ifPresent(recordType -> {
                            r.setRecordType(recordType);
                            repository.save(r);
                        });
                    } else {
                        recordTypeRepository.findById(RecordTypeEnum.OPTIMAL.getId()).ifPresent(recordType -> {
                            r.setRecordType(recordType);
                            repository.save(r);
                        });
                    }
                });
    }

    private Integer getCountSocle(LampDto lamp, Integer countLamp) {
        return (int) Math.ceil((double) countLamp / lamp.getSocle().getPlace());
    }

    private Integer getCountLamp(RoomDto room, LampDto lamp) {
        return (int) Math.ceil(((double) (room.getSquare() * lamp.getLuminousFlux())) / room.getRoomType().getLightningRate());
    }

    public void saveExisting(RecordDto recordDto) {
        RoomDto room = recordDto.getRoom();
        LampDto lamp = recordDto.getLamp();
        Integer countLamp = recordDto.getCountLamp();
        Integer countSocle = getCountSocle(lamp, countLamp);
        setRecordDtoFields(lamp, countLamp, room, recordDto, countSocle, RecordTypeEnum.EXISTING);
        save(recordDto);

        RecordDto recordEffectivity = new RecordDto();
        Integer countLampEffectivity = getCountLamp(room, lamp);
        Integer countSocleEffectivity = getCountSocle(lamp, countLampEffectivity);
        setRecordDtoFields(recordDto.getLamp(), countLampEffectivity, room, recordEffectivity, countSocleEffectivity, RecordTypeEnum.ADDITIONAL);
        recordEffectivity.setCountLamp(countLampEffectivity - countLamp);
        recordEffectivity.setCountSocle(countSocleEffectivity - countSocle);
        double lampsOnAllYears = lamp.getPrice() * (recordEffectivity.getCountLamp() * room.getYearCount() * room.getHoursOfUses() * 365) / lamp.getTermOfWork();
        double electricEnergy = (lamp.getPower() * room.getYearCount() * room.getHoursOfUses() * 365 * 1.66) / 1000;
        recordEffectivity.setSum(recordEffectivity.getCountSocle() * lamp.getSocle().getPrice() + lampsOnAllYears + recordEffectivity.getCountLamp() * electricEnergy);
        save(recordEffectivity);
    }

    private void setRecordDtoFields(LampDto lamp, Integer countLamp, RoomDto room, RecordDto recordDto, Integer countSocle, RecordTypeEnum existing) {
        double lampsOnAllYears = lamp.getPrice() * (countLamp * room.getYearCount() * room.getHoursOfUses() * 365) / lamp.getTermOfWork();
        double electricEnergy = (lamp.getPower() * room.getYearCount() * room.getHoursOfUses() * 365 * 1.66) / 1000;
        recordDto.setCountLamp(countLamp);
        recordDto.setCountSocle(countSocle);
        recordDto.setSum(countSocle * lamp.getSocle().getPrice() + lampsOnAllYears + countLamp * electricEnergy);
        recordDto.setYearCount(room.getYearCount());
        recordDto.setRoom(room);
        recordDto.setLamp(lamp);
        recordTypeRepository.findById(existing.getId()).map(recordTypeMapper::toDto).ifPresent(recordDto::setRecordType);
    }

    public void deleteAllByRoomId(Long id) {
        repository.deleteAllByRoomId(id);
    }
}
