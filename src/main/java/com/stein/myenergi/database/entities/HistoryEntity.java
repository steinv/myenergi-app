package com.stein.myenergi.database.entities;

import com.stein.myenergi.api.dto.HistoryDay;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "history")
@Getter
@Setter
public class HistoryEntity implements Serializable {

    @Id
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne
    private ZappiEntity zappi;

    // electricity in joules imported from the net
    private int imported;
    // electricity in joules exported to the net
    private int exported;

    // electricity in joules generated by solar panels
    private int generated;

    // electricity in joules charged into electrical vehicle
    private int charged;

    // electricity in joules consumed
    private int consumed;

    // original data from api
    private HistoryDay[] history;

    // TODO add aggregated fields
}
