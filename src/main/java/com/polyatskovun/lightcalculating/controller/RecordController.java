package com.polyatskovun.lightcalculating.controller;

import com.polyatskovun.lightcalculating.dto.RecordDto;
import com.polyatskovun.lightcalculating.service.RecordService;
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
@RequestMapping("records")
public class RecordController {

    private final RecordService service;

    public RecordController(RecordService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public RecordDto save(@RequestBody RecordDto dto) {
        return service.save(dto);
    }

    @GetMapping
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public List<RecordDto> findAll() {
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
