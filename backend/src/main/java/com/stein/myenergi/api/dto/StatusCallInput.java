package com.stein.myenergi.api.dto;

import com.stein.myenergi.DeviceType;

public class StatusCallInput implements MyenergiCallInput {

    private DeviceType deviceType;
    private String serial;

    public StatusCallInput() {
        this.deviceType = DeviceType.WILDCARD;
    }

    public StatusCallInput(DeviceType d) {
        this.deviceType = d;
    }

    public StatusCallInput(DeviceType deviceType, String serial) {
        this.deviceType = deviceType;
        this.serial = serial;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    @Override
    public Object[] getParameters() {
        return null;
    }
}
