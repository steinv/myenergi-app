package com.stein.myenergi.api.calls;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import com.stein.myenergi.Device;
import com.stein.myenergi.api.calls.dto.DayCallInput;
import com.stein.myenergi.api.calls.dto.DayCallOutput;
import java.util.GregorianCalendar;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DayCallTest {

    private static final String DUMMY_SERIAL = "abc123";

    @Autowired
    private DayCall dayCall;

    @Test
    void getCommand_zappi3rdOfJune2021_returnsCommand() {
        GregorianCalendar thirdOfJune2021 = new GregorianCalendar(2021, 5, 3);
        String cmd = this.dayCall.getCommand(new DayCallInput(Device.ZAPPI, DUMMY_SERIAL, thirdOfJune2021));

        assertThat(cmd).isEqualTo(String.format(DayCall.COMMAND, Device.ZAPPI) + DUMMY_SERIAL + "-2021-6-3");
    }

    @Test
    void fire() {
        GregorianCalendar thirtyFirstOfAugust2021 = new GregorianCalendar(2021, 7, 31);
        DayCallOutput dayCallOutput = this.dayCall.fire(new DayCallInput(Device.ZAPPI, "16189184", thirtyFirstOfAugust2021));

        assertThat(dayCallOutput.getHistoryDay()).isNotEmpty();
    }
}
