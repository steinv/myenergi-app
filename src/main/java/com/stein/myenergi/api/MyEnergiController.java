package com.stein.myenergi.api;

import com.stein.myenergi.api.calls.dto.HistoryDay;
import com.stein.myenergi.api.calls.dto.Zappi;
import com.stein.myenergi.service.MyEnergiService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyEnergiController {

    private final MyEnergiService service;

    @Autowired
    public MyEnergiController(
            MyEnergiService service
    ) {
        this.service = service;
    }

    @GetMapping("/zappi")
    public Zappi[] getAllZappiStatus() {
        return this.service.getZappiStatus(null).getZappi();
    }

    @GetMapping("/zappi/{serial}")
    public Zappi getSpecificZappi(@PathVariable("serial") String serial) {
        return this.service.getZappiStatus(serial).getZappi()[0];
    }

    @GetMapping("/zappi/{serial}/{date}")
    public HistoryDay[] getZappiHistoryByDate(@PathVariable("serial") String serial, @PathVariable("date") String date) throws ParseException {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(date));
        return this.service.getZappiHistory(serial, gregorianCalendar);
    }
}
