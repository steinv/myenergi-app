package com.stein.myenergi.database;

import com.stein.myenergi.database.entities.ZappiEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZappiRepository extends CrudRepository<ZappiEntity, String> {

}
