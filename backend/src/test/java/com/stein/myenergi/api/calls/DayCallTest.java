package com.stein.myenergi.api.calls;

import com.stein.myenergi.DeviceType;
import com.stein.myenergi.api.dto.DayCallInput;
import com.stein.myenergi.api.dto.DayCallOutput;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.GregorianCalendar;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class DayCallTest {

    private static final String DUMMY_SERIAL = "abc123";

    @Autowired
    private DayCall dayCall;

    @Test
    void getCommand_zappi3rdOfJune2021_returnsCommand() {
        GregorianCalendar thirdOfJune2021 = new GregorianCalendar(2021, 5, 3);
        String cmd = this.dayCall.getCommand(new DayCallInput(DeviceType.ZAPPI, DUMMY_SERIAL, thirdOfJune2021.getTime()));

        assertThat(cmd).isEqualTo(String.format(DayCall.COMMAND, DeviceType.ZAPPI) + DUMMY_SERIAL + "-2021-6-3");
    }

    @Test
    void fire() {
        GregorianCalendar thirtyFirstOfAugust2021 = new GregorianCalendar(2021, 7, 31);
        DayCallOutput dayCallOutput = this.dayCall.fire(new DayCallInput(DeviceType.ZAPPI, "16189184", thirtyFirstOfAugust2021.getTime()));

        assertThat(dayCallOutput.getHistoryDay()).isNotEmpty();
    }
}
