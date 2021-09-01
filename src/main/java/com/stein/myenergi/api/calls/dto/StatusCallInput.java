package com.stein.myenergi.api.calls.dto;

import com.stein.myenergi.Device;
import lombok.Getter;
import org.springframework.lang.Nullable;

@Getter
public class StatusCallInput implements MyenergiCallInput {

    private Device device;
    private String serial;

    public StatusCallInput() {
        this.device = Device.WILDCARD;
    }

    public StatusCallInput(Device d) {
        this.device = d;
    }

    public StatusCallInput(Device d, @Nullable String serial) {
        this.device = d;
        this.serial = serial;
    }

    @Override
    public Object[] getParameters() {
        return null;
    }
}
