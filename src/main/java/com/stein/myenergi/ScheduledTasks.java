package com.stein.myenergi;

import com.stein.myenergi.api.dto.HistoryDay;
import com.stein.myenergi.api.dto.Zappi;
import com.stein.myenergi.database.HistoryRepository;
import com.stein.myenergi.database.entities.HistoryEntity;
import com.stein.myenergi.database.entities.ZappiEntity;
import com.stein.myenergi.service.MyEnergiApiService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.GregorianCalendar;

@Component
public class ScheduledTasks {

    private MyEnergiApiService service;
    private ModelMapper modelMapper;
    private HistoryRepository historyRepository;

    @Autowired
    public ScheduledTasks(MyEnergiApiService service, ModelMapper modelMapper, HistoryRepository historyRepository) {
        this.service = service;
        this.modelMapper = modelMapper;
        this.historyRepository = historyRepository;
    }
    /**
     * Daily, at 6am, persists historic data of your zappi device(s)
     */
    @Scheduled(cron = "0 0 6 * * *")
    public void persistHistoricZappiData() {
        Zappi[] zappis = this.service.getZappiStatus(null).getZappi();
        GregorianCalendar[] dates = this.datesToFetch();

        for(GregorianCalendar date: dates) {
            for (Zappi zappi : zappis) {
                HistoryDay[] zappiHistory = this.service.getZappiHistory(zappi.getSerialNumber(), date);
                HistoryEntity entity = modelMapper.map(zappiHistory, HistoryEntity.class);
                entity.setZappi(new ZappiEntity(zappi.getSerialNumber()));
                entity.setDate(date.getTime());

                historyRepository.save(entity);
            }
        }
    }

    // TODO determine which date(s) to fetch
    private GregorianCalendar[] datesToFetch() {
        GregorianCalendar yesterday = new GregorianCalendar();
        yesterday.add(Calendar.DATE, -1);

        return new GregorianCalendar[]{ yesterday };
    }
}
