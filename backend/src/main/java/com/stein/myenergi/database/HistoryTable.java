package com.stein.myenergi.database;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import com.stein.myenergi.database.entities.HistoryEntity;
import com.stein.myenergi.database.entities.HistoryId;

public interface HistoryTable {
    public Optional<HistoryEntity> findById(HistoryId historyId);

    public HistoryEntity save(HistoryEntity entity);

    public Optional<Collection<HistoryEntity>> findByPeriod(String serial, Date start, Date end);
}
