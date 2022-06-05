package com.polyatskovun.lightcalculating.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Lamp extends IdEntity {

    @Column
    private Double price;

    @Column(name = "term_of_work")
    private Integer termOfWork;

    @Column(name = "luminous_flux")
    private Integer luminousFlux;

    @Column
    private Integer power;

    @Column
    private String model;

    @ManyToOne
    @JoinColumn(name = "lamp_type_id", referencedColumnName = "id")
    private LampType lampType;

    @ManyToOne
    @JoinColumn(name = "socle_id", referencedColumnName = "id")
    private Socle socle;

    @Builder(toBuilder = true)
    public Lamp(Long id, Double price, Integer termOfWork, Integer luminousFlux,
                Integer power, String model, LampType lampType, Socle socle) {
        super(id);
        this.price = price;
        this.termOfWork = termOfWork;
        this.luminousFlux = luminousFlux;
        this.power = power;
        this.model = model;
        this.lampType = lampType;
        this.socle = socle;
    }
}
