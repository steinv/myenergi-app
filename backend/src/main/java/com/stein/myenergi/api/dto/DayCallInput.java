package com.stein.myenergi.api.dto;

import com.stein.myenergi.DeviceType;
import lombok.Getter;

import java.util.Date;

@Getter
public class DayCallInput implements MyenergiCallInput {

    private final DeviceType deviceType;
    private final String serial;
    private final Date date;

    public DayCallInput(DeviceType deviceType, String serial, Date date) {
        this.deviceType = deviceType;
        this.serial = serial;
        this.date = date;
    }

    @Override
    public Object[] getParameters() {
        return null;
    }
}
