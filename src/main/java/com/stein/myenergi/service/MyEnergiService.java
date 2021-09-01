package com.stein.myenergi.service;

import com.stein.myenergi.Device;
import com.stein.myenergi.api.calls.DayCall;
import com.stein.myenergi.api.calls.StatusCall;
import com.stein.myenergi.api.calls.dto.DayCallInput;
import com.stein.myenergi.api.calls.dto.HistoryDay;
import com.stein.myenergi.api.calls.dto.StatusCallInput;
import com.stein.myenergi.api.calls.dto.StatusCallOutput;
import java.util.GregorianCalendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
/**
 * TODO This service should persist data for performance and data retention
 */
public class MyEnergiService {

    private final DayCall dayCall;
    private final StatusCall statusCall;

    @Autowired
    public MyEnergiService(
            DayCall dayCall,
            StatusCall statusCall
    ) {
        this.dayCall = dayCall;
        this.statusCall = statusCall;
    }

    public StatusCallOutput getZappiStatus(@Nullable String serial) {
        return statusCall.fire(new StatusCallInput(Device.ZAPPI, serial));
    }

    public HistoryDay[] getZappiHistory(String serial, GregorianCalendar date) {
        // TODO Be sure to retain data in database for performance.
        return dayCall.fire(new DayCallInput(Device.ZAPPI, serial, date)).getHistoryDay();
    }
}
