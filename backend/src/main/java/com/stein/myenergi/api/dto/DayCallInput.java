package com.stein.myenergi.api.dto;

import com.stein.myenergi.DeviceType;

import java.util.Date;

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

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public String getSerial() {
        return serial;
    }

    public Date getDate() {
        return date;
    }
}
