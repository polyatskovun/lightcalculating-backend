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
public class Room extends IdEntity {

    @Column
    private Integer square;

    @Column
    private String number;

    @Column
    private Double hoursOfUses;

    @ManyToOne
    @JoinColumn(name = "room_type_id", referencedColumnName = "id")
    private RoomType roomType;

    @Builder(toBuilder = true)
    public Room(Long id, Integer square, Double hoursOfUses, String number, RoomType roomType) {
        super(id);
        this.square = square;
        this.number = number;
        this.roomType = roomType;
        this.hoursOfUses = hoursOfUses;
    }
}
