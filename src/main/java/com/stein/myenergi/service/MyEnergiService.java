package com.stein.myenergi.service;

import com.stein.myenergi.MyEnergiDevice;
import com.stein.myenergi.api.calls.DayCall;
import com.stein.myenergi.api.calls.StatusCall;
import com.stein.myenergi.api.dto.DayCallInput;
import com.stein.myenergi.api.dto.HistoryDay;
import com.stein.myenergi.api.dto.StatusCallInput;
import com.stein.myenergi.api.dto.StatusCallOutput;
import com.stein.myenergi.database.HistoryRepository;
import com.stein.myenergi.database.ZappiRepository;
import com.stein.myenergi.database.entities.HistoryEntity;
import com.stein.myenergi.database.entities.ZappiEntity;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
public class MyEnergiService {

    private final DayCall dayCall;
    private final StatusCall statusCall;
    private final HistoryRepository historyRepository;
    private final ZappiRepository zappiRepository;

    @Autowired
    public MyEnergiService(
            DayCall dayCall,
            StatusCall statusCall,
            ZappiRepository zappiRepository,
            HistoryRepository historyRepository
    ) {
        this.dayCall = dayCall;
        this.statusCall = statusCall;
        this.zappiRepository = zappiRepository;
        this.historyRepository = historyRepository;
    }

    public StatusCallOutput getZappiStatus(@Nullable String serial) {
        return statusCall.fire(new StatusCallInput(MyEnergiDevice.ZAPPI, serial));
    }

    public HistoryDay[] getZappiHistory(String serial, GregorianCalendar date) {
        ZappiEntity zappi = this.zappiRepository.findById(serial).orElseGet(() -> this.zappiRepository.save(new ZappiEntity(serial)));
        System.out.println(zappiRepository.count());
        List<HistoryEntity> zappiHistory = zappi.getHistory().stream()
                .filter((historyEntity -> historyEntity.getDate().getTime() == date.getTime().getTime()))
                .collect(Collectors.toList());

        // zappiHistory.isEmpty()
        /**
         .orElseGet(() -> {
         HistoryDay[] historyDay = dayCall.fire(new DayCallInput(MyEnergiDevice.ZAPPI, serial, date)).getHistoryDay();
         return null;
         });
         */
        return dayCall.fire(new DayCallInput(MyEnergiDevice.ZAPPI, serial, date)).getHistoryDay();
    }
}
