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
public class RecordDto implements Serializable {

    private Long id;
    private Integer countLamp;
    private Integer countSocle;
    private Integer yearCount;
    private Double sum;
    private RoomDto room;
    private LampDto lamp;
    private RecordTypeDto recordType;
}
