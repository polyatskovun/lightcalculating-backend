package com.polyatskovun.lightcalculating.domain.enums;

import lombok.Getter;

@Getter
public enum RecordTypeEnum {
    EXISTING(1, "Existing"), NEW(2, "New"), ADDITIONAL(3, "Additional"), OPTIMAL(4, "Optimal");

    private final long id;

    private final String name;

    RecordTypeEnum(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
