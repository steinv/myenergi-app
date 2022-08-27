package com.stein.myenergi.database.sql;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stein.myenergi.database.HistoryTable;
import com.stein.myenergi.database.entities.HistoryEntity;
import com.stein.myenergi.database.entities.HistoryId;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Repository
public interface HistoryRepository extends HistoryTable, CrudRepository<HistoryEntity, HistoryId> {
    
    @Query("SELECT h FROM history h WHERE (h.id.serial = :serial AND h.id.date >= :start AND h.id.date <= :end)")
    Optional<Collection<HistoryEntity>> findByPeriod(@Param("serial") String serial, @Param("start") Date start, @Param("end")Date end);
}
