package com.stein.myenergi.database.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;

@Entity
@Table(name = "history")
@Getter
public class HistoryEntity implements Serializable {

    @Id
    @Temporal(TemporalType.DATE)
    private Date date;

    // TODO add fields
}
