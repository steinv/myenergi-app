package com.stein.myenergi.database.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "zappi")
public class ZappiEntity implements Serializable {

    public ZappiEntity(String serial) {
        this.serial = serial;
    }

    @Id
    private String serial;

    @OneToMany(targetEntity = HistoryEntity.class)
    private List<HistoryEntity> history;
}
