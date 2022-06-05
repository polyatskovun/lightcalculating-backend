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
public class Socle extends IdEntity{

    @Column
    private String name;

    @Column
    private Double price;

    @Column
    private Integer place;

    @Builder(toBuilder = true)
    public Socle(Long id, String name, Double price, Integer place) {
        super(id);
        this.name = name;
        this.price = price;
        this.place = place;
    }
}
