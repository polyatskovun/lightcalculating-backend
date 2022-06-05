package com.polyatskovun.lightcalculating.repository;

import com.polyatskovun.lightcalculating.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {

}