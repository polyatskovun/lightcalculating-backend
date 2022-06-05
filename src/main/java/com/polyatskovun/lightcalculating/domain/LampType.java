package com.polyatskovun.lightcalculating.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
public class LampType extends IdEntity {

    @Column
    private String name;

    @Builder(toBuilder = true)
    public LampType(Long id, String name) {
        super(id);
        this.name = name;
    }
}
