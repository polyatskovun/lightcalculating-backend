package com.polyatskovun.lightcalculating.repository;

import com.polyatskovun.lightcalculating.domain.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long> {

    List<Record> findAllByRoomId(Long roomId);

    void deleteAllByRoomId(Long id);
}