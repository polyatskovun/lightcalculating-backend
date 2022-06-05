package com.polyatskovun.lightcalculating.controller;

import com.polyatskovun.lightcalculating.dto.LampTypeDto;
import com.polyatskovun.lightcalculating.service.LampTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/lampTypes")
public class LampTypeController {

    private final LampTypeService service;

    public LampTypeController(LampTypeService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public List<LampTypeDto> findAll() {
        return service.findAll();
    }
}
