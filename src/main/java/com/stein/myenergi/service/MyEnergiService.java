package com.stein.myenergi.service;

import com.stein.myenergi.api.calls.ZappiStatusCall;
import com.stein.myenergi.api.calls.dto.Zappi;
import com.stein.myenergi.api.calls.dto.ZappiStatusCallInput;
import com.stein.myenergi.api.calls.dto.ZappiStatusCallOutput;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
/**
 * TODO This service should persist data for performance and data retention
 */
public class MyEnergiService {

    private final ZappiStatusCall zappiStatusCall;

    public MyEnergiService(
            ZappiStatusCall zappiStatusCall
    ) {
        this.zappiStatusCall = zappiStatusCall;
    }

    public ZappiStatusCallOutput getZappiStatus(@Nullable String serial) {
        if(StringUtils.hasText(serial)) {
            return zappiStatusCall.fire(new ZappiStatusCallInput(serial));
        } else {
            return zappiStatusCall.fire(new ZappiStatusCallInput());
        }
    }
}
