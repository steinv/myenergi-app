package com.stein.myenergi.database;

import com.stein.myenergi.database.entities.HistoryEntity;
import com.stein.myenergi.database.entities.ZappiEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class HistoryRepositoryTest {
    @Autowired
    private HistoryRepository repo;

    private static final ZappiEntity ZAPPI_ENTITY = new ZappiEntity("DUMMY");
    private static final HistoryEntity HISTORY_ENTITY = new HistoryEntity();

    @BeforeEach
    public void setUp() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, Calendar.SEPTEMBER, 23);

        HISTORY_ENTITY.setZappi(ZAPPI_ENTITY);
        HISTORY_ENTITY.setDate(calendar.getTime());
        HISTORY_ENTITY.setCharged(1000);
        HISTORY_ENTITY.setImported(6000);
        HISTORY_ENTITY.setExported(3000);
    }
    @Test
    public void test_insertHistoryEntity_shouldAddEntityToDb() {
        repo.save(HISTORY_ENTITY);
    }
}
