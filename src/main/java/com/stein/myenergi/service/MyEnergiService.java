package com.stein.myenergi.service;

import com.stein.myenergi.MyEnergiDevice;
import com.stein.myenergi.api.calls.DayCall;
import com.stein.myenergi.api.calls.StatusCall;
import com.stein.myenergi.api.dto.DayCallInput;
import com.stein.myenergi.api.dto.HistoryDay;
import com.stein.myenergi.api.dto.StatusCallInput;
import com.stein.myenergi.api.dto.StatusCallOutput;
import java.util.GregorianCalendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
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
        return statusCall.fire(new StatusCallInput(MyEnergiDevice.ZAPPI, serial));
    }

    public HistoryDay[] getZappiHistory(String serial, GregorianCalendar date) {
        /**
         * TODO
         * Check if the serial/date is already available in the db
         * Be sure to retain data from "dayCall" in database for performance.
         */
        return dayCall.fire(new DayCallInput(MyEnergiDevice.ZAPPI, serial, date)).getHistoryDay();
    }
}
