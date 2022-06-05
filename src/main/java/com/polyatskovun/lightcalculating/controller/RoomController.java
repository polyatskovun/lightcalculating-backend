package com.polyatskovun.lightcalculating.controller;

import com.polyatskovun.lightcalculating.dto.RoomDto;
import com.polyatskovun.lightcalculating.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService service;

    public RoomController(RoomService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public RoomDto save(@RequestBody RoomDto dto) {
        return service.save(dto);
    }

    @GetMapping
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public List<RoomDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public RoomDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
