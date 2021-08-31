package com.stein.myenergi.api.calls;

import static org.assertj.core.api.Assertions.assertThat;

import com.stein.myenergi.api.calls.dto.Zappi;
import com.stein.myenergi.api.calls.dto.ZappiStatusCallInput;
import com.stein.myenergi.api.calls.dto.ZappiStatusCallOutput;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ZappiStatusCallTest {

    @Autowired
    private ZappiStatusCall zappiStatusCall;

    @Test
    void getCommand_withoutSerial_shouldReturnZCommand() {
        String cmd = zappiStatusCall.getCommand(new ZappiStatusCallInput());

        assertThat(cmd).isEqualTo(ZappiStatusCall.COMMAND);
    }

    @Test
    void getCommand_withSerial_shouldReturnZCommandWithSerial() {
        String dummmySerial = "abc123";
        String cmd = zappiStatusCall.getCommand(new ZappiStatusCallInput(dummmySerial));

        assertThat(cmd).isEqualTo(ZappiStatusCall.COMMAND + dummmySerial);
    }

    @Test
    void fire_shouldRetrieveStatus() {
        ZappiStatusCallOutput output = zappiStatusCall.fire(new ZappiStatusCallInput("16189184"));
        Zappi[] zappis = output.getZappi();

        assertThat(zappis).isNotEmpty();
    }
}
