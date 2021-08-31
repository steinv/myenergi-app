package com.stein.myenergi.api;

import com.stein.myenergi.api.calls.ZappiStatusCall;
import com.stein.myenergi.api.calls.dto.NoInput;
import com.stein.myenergi.api.calls.dto.ZappiStatusCallInput;
import com.stein.myenergi.api.calls.dto.ZappiStatusCallOutput;
import com.stein.myenergi.api.calls.dto.Zappi;
import com.stein.myenergi.service.MyEnergiService;
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
    public String getZappiHistoryByDate(@PathVariable("serial") String serial, @PathVariable("date") String date) {
        // TODO implement on MyEnergiService. Be sure to retain data in database for performance
        return null;
    }
}
