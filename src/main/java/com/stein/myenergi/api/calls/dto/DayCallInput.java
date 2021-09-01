package com.stein.myenergi.api.calls.dto;

import com.stein.myenergi.Device;
import java.util.GregorianCalendar;
import lombok.Getter;

@Getter
public class DayCallInput implements MyenergiCallInput {

    private final Device device;
    private final String serial;
    private final GregorianCalendar date;

    public DayCallInput(Device device, String serial, GregorianCalendar date) {
        this.device = device;
        this.serial = serial;
        this.date = date;
    }

    @Override
    public Object[] getParameters() {
        return null;
    }
}
