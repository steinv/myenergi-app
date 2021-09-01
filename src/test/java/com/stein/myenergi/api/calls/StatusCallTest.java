package com.stein.myenergi.api.calls;

import static org.assertj.core.api.Assertions.assertThat;

import com.stein.myenergi.MyEnergiDevice;
import com.stein.myenergi.api.dto.StatusCallInput;
import com.stein.myenergi.api.dto.StatusCallOutput;
import com.stein.myenergi.api.dto.Zappi;
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

        assertThat(cmd).isEqualTo(String.format(StatusCall.COMMAND, MyEnergiDevice.WILDCARD));
    }

    @Test
    void getCommand_withDeviceAndSerial_shouldReturnZCommandWithSerial() {
        String dummmySerial = "abc123";
        String cmd = statusCall.getCommand(new StatusCallInput(MyEnergiDevice.ZAPPI, dummmySerial));

        assertThat(cmd).isEqualTo(String.format(StatusCall.COMMAND, MyEnergiDevice.ZAPPI) + dummmySerial);
    }

    @Test
    void fire_shouldRetrieveStatus() {
        StatusCallOutput output = statusCall.fire(new StatusCallInput(MyEnergiDevice.ZAPPI, "16189184"));
        Zappi[] zappis = output.getZappi();

        assertThat(zappis).isNotEmpty();
    }
}
