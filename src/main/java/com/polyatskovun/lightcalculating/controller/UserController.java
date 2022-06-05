package com.polyatskovun.lightcalculating.controller;

import com.polyatskovun.lightcalculating.dto.UserDto;
import com.polyatskovun.lightcalculating.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public UserDto save(@RequestBody UserDto dto) {
        return service.save(dto);
    }

    @PostMapping("/login")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public UserDto login(@RequestBody UserDto dto) {
        return service.login(dto);
    }
}
