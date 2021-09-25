package com.stein.myenergi.service;

import com.stein.myenergi.api.dto.HistoryDay;
import com.stein.myenergi.database.HistoryRepository;
import com.stein.myenergi.database.entities.HistoryEntity;
import com.stein.myenergi.database.entities.HistoryId;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
@RequiredArgsConstructor
public class MyEnergiService {

    private final ModelMapper modelMapper;
    private final MyEnergiApiService apiService;
    private final HistoryRepository historyRepository;

    public void persistZappiData(String zappiSerial, Calendar date) {
        HistoryDay[] zappiHistory = this.apiService.getZappiHistory(zappiSerial, date);
        HistoryEntity entity = modelMapper.map(zappiHistory, HistoryEntity.class);
        entity.setId(new HistoryId(date.getTime(), zappiSerial));
        this.historyRepository.save(entity);
    }
}
