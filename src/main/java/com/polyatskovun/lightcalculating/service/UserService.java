package com.polyatskovun.lightcalculating.service;

import com.polyatskovun.lightcalculating.domain.User;
import com.polyatskovun.lightcalculating.dto.UserDto;
import com.polyatskovun.lightcalculating.mapper.UserMapper;
import com.polyatskovun.lightcalculating.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
@Transactional
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public UserService(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public UserDto save(UserDto dto) {
        User entity = mapper.toEntity(dto);
        entity.setPassword(encrypt(dto.getPassword()));
        return mapper.toDto(repository.save(entity));
    }

    public UserDto login(UserDto dto) {
        dto.setIsAccess(false);
        repository.findByUsername(dto.getUsername()).ifPresent(u -> {
            if (u.getPassword().equals(encrypt(dto.getPassword()))) {
                dto.setIsAccess(true);
            }
        });
        return dto;
    }

    public String encrypt(String pwd) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException();
        }
        md.update(pwd.getBytes());
        byte[] bytes = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
