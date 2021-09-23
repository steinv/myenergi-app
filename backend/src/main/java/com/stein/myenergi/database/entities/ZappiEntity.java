package com.stein.myenergi.database.entities;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Getter
@Table(name = "zappi")
public class ZappiEntity implements Serializable {

    public ZappiEntity() {
        // default constructor
    }

    public ZappiEntity(String serial) {
        this.serial = serial;
    }

    @Id
    private String serial;
}
