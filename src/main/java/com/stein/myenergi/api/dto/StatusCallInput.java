package com.stein.myenergi.api.dto;

import com.stein.myenergi.DeviceType;
import lombok.Getter;
import org.springframework.lang.Nullable;

@Getter
public class StatusCallInput implements MyenergiCallInput {

    private DeviceType deviceType;
    private String serial;

    public StatusCallInput() {
        this.deviceType = DeviceType.WILDCARD;
    }

    public StatusCallInput(DeviceType d) {
        this.deviceType = d;
    }

    public StatusCallInput(DeviceType d, @Nullable String serial) {
        this.deviceType = d;
        this.serial = serial;
    }

    @Override
    public Object[] getParameters() {
        return null;
    }
}
