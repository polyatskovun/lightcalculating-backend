package com.polyatskovun.lightcalculating.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LampDto implements Serializable {

    private Long id;
    private Double price;
    private Integer termOfWork;
    private Integer luminousFlux;
    private Integer power;
    private String model;
    private LampTypeDto lampType;
    private SocleDto socle;
}
