package com.stein.myenergi.api.calls;

import com.stein.myenergi.api.calls.dto.ZappiStatusCallInput;
import com.stein.myenergi.api.calls.dto.ZappiStatusCallOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

@Component
public class ZappiStatusCall extends AbstractMyEnergiCall<ZappiStatusCallInput, ZappiStatusCallOutput> {

    public static final String COMMAND = "/cgi-jstatus-Z";

    @Autowired
    public ZappiStatusCall(RestTemplate myEnergyRestTemplate) {
        super(myEnergyRestTemplate, ZappiStatusCallOutput.class);
    }

    @Override
    String getCommand(ZappiStatusCallInput input) {
        if(StringUtils.hasLength(input.getSerial())) {
            return COMMAND + input.getSerial();
        }
        return COMMAND;
    }
}
