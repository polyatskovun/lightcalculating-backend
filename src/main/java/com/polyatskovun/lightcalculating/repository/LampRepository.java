package com.polyatskovun.lightcalculating.repository;

import com.polyatskovun.lightcalculating.domain.Lamp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LampRepository extends JpaRepository<Lamp, Long> {

}