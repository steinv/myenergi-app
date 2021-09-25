package com.stein.myenergi.service;

import com.stein.myenergi.api.dto.HistoryDay;
import com.stein.myenergi.database.HistoryRepository;
import com.stein.myenergi.database.entities.HistoryEntity;
import com.stein.myenergi.database.entities.HistoryId;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class MyEnergiService {

    private final ModelMapper modelMapper;
    private final MyEnergiApiService apiService;
    private final HistoryRepository historyRepository;

    /**
     * Persist Historical data for a zappi serial on a certain point in time;
     * @param zappiSerial
     * @param date
     */
    public void persistZappiData(String zappiSerial, Date date) {
        HistoryDay[] zappiHistory = this.apiService.getZappiHistory(zappiSerial, date);
        HistoryEntity entity = modelMapper.map(zappiHistory, HistoryEntity.class);
        entity.setId(new HistoryId(date, zappiSerial));
        this.historyRepository.save(entity);
    }

    /**
     * Finds data for a zappi serial on a certain point in time.
     * Looks in the repository and uses api-call as backup
     * @param serial
     * @param date
     * @return HistoryEntity
     */
    public HistoryEntity findHistoricData(String serial, Date date) {
        HistoryId id = new HistoryId(date, serial);
        return this.historyRepository.findById(id).orElseGet(() -> {
            HistoryDay[] zappiHistory = this.apiService.getZappiHistory(serial, date);
            return modelMapper.map(zappiHistory, HistoryEntity.class);
        });
    }

    public Collection<HistoryEntity> findHistoricData(String serial, Date start, Date end) {
        return this.historyRepository.findByPeriod(serial, start, end).orElse(Collections.emptyList());
    }
}
