package com.polyatskovun.lightcalculating.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomDto implements Serializable {

    private Long id;
    private Integer square;
    private String number;
    private Double hoursOfUses;
    private RoomTypeDto roomType;

    private List<RecordDto> records;
    private Integer yearCount;
}
