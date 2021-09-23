package com.stein.myenergi.api.calls;

import com.stein.myenergi.api.dto.StatusCallInput;
import com.stein.myenergi.api.dto.StatusCallOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

@Component
public class StatusCall extends AbstractMyEnergiCall<StatusCallInput, StatusCallOutput> {

    public static final String COMMAND = "/cgi-jstatus-%s";

    @Autowired
    public StatusCall(RestTemplate myEnergyRestTemplate) {
        super(myEnergyRestTemplate, StatusCallOutput.class);
    }

    @Override
    String getCommand(StatusCallInput input) {
        String cmd = String.format(COMMAND, input.getDeviceType().toString());

        if (StringUtils.hasText(input.getSerial())) {
            return cmd + input.getSerial();
        }

        return cmd;
    }
}
