package com.polyatskovun.lightcalculating.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
public class RecordType extends IdEntity {

    private String description;

    @Builder(toBuilder = true)
    public RecordType(Long id, String description) {
        super(id);
        this.description = description;
    }
}
