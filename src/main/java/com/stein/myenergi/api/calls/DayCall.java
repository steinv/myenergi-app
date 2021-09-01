package com.stein.myenergi.api.calls;

import com.stein.myenergi.api.dto.DayCallInput;
import com.stein.myenergi.api.dto.DayCallOutput;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DayCall extends AbstractMyEnergiCall<DayCallInput, DayCallOutput> {

    public static final String COMMAND = "/cgi-jday-%s";
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-M-d", Locale.getDefault());

    @Autowired
    public DayCall(RestTemplate myEnergyRestTemplate) {
        super(myEnergyRestTemplate, DayCallOutput.class);
    }

    @Override
        // example cgi-jday-Z16189184-2021-8-30
    String getCommand(DayCallInput input) {
        return String.format(COMMAND, input.getMyEnergiDevice().toString())
                + input.getSerial()
                + "-"
                + dateTimeFormatter.format(input.getDate().toZonedDateTime());
    }
}
