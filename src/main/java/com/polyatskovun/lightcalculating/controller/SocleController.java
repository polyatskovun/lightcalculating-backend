package com.polyatskovun.lightcalculating.controller;

import com.polyatskovun.lightcalculating.dto.SocleDto;
import com.polyatskovun.lightcalculating.service.SocleService;
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
@RequestMapping("/socles")
public class SocleController {

    private final SocleService service;

    public SocleController(SocleService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public SocleDto save(@RequestBody SocleDto dto) {
        return service.save(dto);
    }

    @GetMapping
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public List<SocleDto> findAll() {
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
