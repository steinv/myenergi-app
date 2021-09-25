package com.stein.myenergi.database;

import com.stein.myenergi.database.entities.HistoryEntity;
import com.stein.myenergi.database.entities.HistoryId;
import com.stein.myenergi.database.entities.ZappiEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class HistoryRepositoryTest {
    @Autowired
    private HistoryRepository repo;

    private Calendar calendar;
    private static final ZappiEntity ZAPPI_ENTITY = new ZappiEntity("DUMMY");
    private static final HistoryEntity HISTORY_ENTITY = new HistoryEntity();

    @BeforeEach
    public void setUp() {
        calendar = Calendar.getInstance();
        calendar.set(2021, Calendar.SEPTEMBER, 23);

        HISTORY_ENTITY.setId(new HistoryId(calendar.getTime(), ZAPPI_ENTITY));
        HISTORY_ENTITY.setCharged(1000);
        HISTORY_ENTITY.setImported(6000);
        HISTORY_ENTITY.setExported(3000);
    }
    @Test
    public void test_insertHistoryEntity_shouldAddEntityToDb() {
        repo.save(HISTORY_ENTITY);

        assertTrue(repo.findById(new HistoryId(calendar.getTime(), ZAPPI_ENTITY)).get().equals(HISTORY_ENTITY));
    }
}
