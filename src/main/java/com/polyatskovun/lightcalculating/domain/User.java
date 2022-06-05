package com.polyatskovun.lightcalculating.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "user_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class User extends IdEntity {

    @Column
    private String username;

    @Column
    private String password;

    public User(Long id, String username, String password) {
        super(id);
        this.username = username;
        this.password = password;
    }
}
