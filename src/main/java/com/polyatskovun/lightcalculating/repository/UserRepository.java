package com.polyatskovun.lightcalculating.repository;

import com.polyatskovun.lightcalculating.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
