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
public class RoomType extends IdEntity {

    @Column
    private String name;

    @Column(name = "lightning_rate")
    private Integer lightningRate;

    @Builder(toBuilder = true)
    public RoomType(Long id, String name, Integer lightningRate) {
        super(id);
        this.name = name;
        this.lightningRate = lightningRate;
    }
}
