package com.stein.myenergi.api.dto;

import com.stein.myenergi.MyEnergiDevice;
import java.util.GregorianCalendar;
import lombok.Getter;

@Getter
public class DayCallInput implements MyenergiCallInput {

    private final MyEnergiDevice myEnergiDevice;
    private final String serial;
    private final GregorianCalendar date;

    public DayCallInput(MyEnergiDevice myEnergiDevice, String serial, GregorianCalendar date) {
        this.myEnergiDevice = myEnergiDevice;
        this.serial = serial;
        this.date = date;
    }

    @Override
    public Object[] getParameters() {
        return null;
    }
}
