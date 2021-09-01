package com.stein.myenergi.api.dto;

import com.stein.myenergi.MyEnergiDevice;
import lombok.Getter;
import org.springframework.lang.Nullable;

@Getter
public class StatusCallInput implements MyenergiCallInput {

    private MyEnergiDevice myEnergiDevice;
    private String serial;

    public StatusCallInput() {
        this.myEnergiDevice = MyEnergiDevice.WILDCARD;
    }

    public StatusCallInput(MyEnergiDevice d) {
        this.myEnergiDevice = d;
    }

    public StatusCallInput(MyEnergiDevice d, @Nullable String serial) {
        this.myEnergiDevice = d;
        this.serial = serial;
    }

    @Override
    public Object[] getParameters() {
        return null;
    }
}
