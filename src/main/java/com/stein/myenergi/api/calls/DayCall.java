package com.stein.myenergi.api.calls;

import com.stein.myenergi.api.dto.DayCallInput;
import com.stein.myenergi.api.dto.DayCallOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class DayCall extends AbstractMyEnergiCall<DayCallInput, DayCallOutput> {

    public static final String COMMAND = "/cgi-jday-%s";
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-M-d", Locale.getDefault()).withZone(ZoneId.of("Europe/Brussels"));

    @Autowired
    public DayCall(RestTemplate myEnergyRestTemplate) {
        super(myEnergyRestTemplate, DayCallOutput.class);
    }

    @Override
        // example cgi-jday-Z16189184-2021-8-30
    String getCommand(DayCallInput input) {
        return String.format(COMMAND, input.getDeviceType().toString())
                + input.getSerial()
                + "-"
                + dateTimeFormatter.format(input.getDate().toInstant());
    }
}
