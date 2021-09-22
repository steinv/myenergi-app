package com.stein.myenergi.database;

import com.stein.myenergi.database.entities.HistoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface HistoryRepository extends CrudRepository<HistoryEntity, Date> {

}
