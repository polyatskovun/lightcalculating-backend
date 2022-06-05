package com.polyatskovun.lightcalculating.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
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
