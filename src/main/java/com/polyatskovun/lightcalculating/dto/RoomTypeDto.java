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
public class RoomTypeDto implements Serializable {

    private Long id;
    private String name;
    private Integer lightningRate;
}
