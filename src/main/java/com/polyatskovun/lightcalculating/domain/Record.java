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
public class Record extends IdEntity {

    @Column(name = "count_lamp")
    private Integer countLamp;

    @Column(name = "count_socle")
    private Integer countSocle;

    @Column(name = "year_count")
    private Integer yearCount;

    @Column
    private Double sum;

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "lamp_id", referencedColumnName = "id")
    private Lamp lamp;

    @ManyToOne
    @JoinColumn(name = "record_type_id", referencedColumnName = "id")
    private RecordType recordType;

    @Builder(toBuilder = true)
    public Record(Long id, Integer countLamp, Integer countSocle, Integer yearCount,
                  Double sum, Room room, Lamp lamp, RecordType recordType) {
        super(id);
        this.countLamp = countLamp;
        this.countSocle = countSocle;
        this.yearCount = yearCount;
        this.sum = sum;
        this.room = room;
        this.lamp = lamp;
        this.recordType = recordType;
    }
}
