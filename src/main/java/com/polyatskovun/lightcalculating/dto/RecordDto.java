package com.polyatskovun.lightcalculating.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
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
