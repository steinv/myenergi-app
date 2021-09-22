package com.stein.myenergi.service;

import com.stein.myenergi.DeviceType;
import com.stein.myenergi.api.calls.DayCall;
import com.stein.myenergi.api.calls.StatusCall;
import com.stein.myenergi.api.dto.DayCallInput;
import com.stein.myenergi.api.dto.HistoryDay;
import com.stein.myenergi.api.dto.StatusCallInput;
import com.stein.myenergi.api.dto.StatusCallOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.GregorianCalendar;

@Service
public class MyEnergiApiService {

    private final DayCall dayCall;
    private final StatusCall statusCall;
    @Autowired
    public MyEnergiApiService(
            DayCall dayCall,
            StatusCall statusCall
    ) {
        this.dayCall = dayCall;
        this.statusCall = statusCall;
    }

    public StatusCallOutput getZappiStatus(@Nullable String serial) {
        return statusCall.fire(new StatusCallInput(DeviceType.ZAPPI, serial));
    }

    public HistoryDay[] getZappiHistory(String serial, GregorianCalendar date) {
        return dayCall.fire(new DayCallInput(DeviceType.ZAPPI, serial, date)).getHistoryDay();
    }
}
