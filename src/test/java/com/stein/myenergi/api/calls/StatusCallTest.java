package com.stein.myenergi.api.calls;

import static org.assertj.core.api.Assertions.assertThat;

import com.stein.myenergi.Device;
import com.stein.myenergi.api.calls.dto.StatusCallInput;
import com.stein.myenergi.api.calls.dto.StatusCallOutput;
import com.stein.myenergi.api.calls.dto.Zappi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StatusCallTest {

    @Autowired
    private StatusCall statusCall;

    @Test
    void getCommand_withoutSerial_shouldReturnWildCardCommand() {
        String cmd = statusCall.getCommand(new StatusCallInput());

        assertThat(cmd).isEqualTo(String.format(StatusCall.COMMAND, Device.WILDCARD));
    }

    @Test
    void getCommand_withDeviceAndSerial_shouldReturnZCommandWithSerial() {
        String dummmySerial = "abc123";
        String cmd = statusCall.getCommand(new StatusCallInput(Device.ZAPPI, dummmySerial));

        assertThat(cmd).isEqualTo(String.format(StatusCall.COMMAND, Device.ZAPPI) + dummmySerial);
    }

    @Test
    void fire_shouldRetrieveStatus() {
        StatusCallOutput output = statusCall.fire(new StatusCallInput(Device.ZAPPI, "16189184"));
        Zappi[] zappis = output.getZappi();

        assertThat(zappis).isNotEmpty();
    }
}
