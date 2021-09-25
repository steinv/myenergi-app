package com.stein.myenergi;

import com.stein.myenergi.api.dto.Zappi;
import com.stein.myenergi.database.entities.HistoryEntity;
import com.stein.myenergi.dto.DayOutput;
import com.stein.myenergi.dto.HistoryOutput;
import com.stein.myenergi.dto.PersistInput;
import com.stein.myenergi.service.MyEnergiApiService;
import com.stein.myenergi.service.MyEnergiService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class MyEnergiController {

    private final MyEnergiApiService apiService;
    private final MyEnergiService service;

    @GetMapping("/zappi")
    public Zappi[] getAllZappiStatus() {
        return this.apiService.getZappiStatus(null).getZappi();
    }

    @GetMapping("/zappi/{serial}")
    public Zappi getSpecificZappi(@PathVariable("serial") String serial) {
        return this.apiService.getZappiStatus(serial).getZappi()[0];
    }

    @PostMapping("/zappi/{serial}")
    public void persistZappiData(
            @PathVariable("serial") String serial,
            @RequestBody(required = false) PersistInput input
    ){
        if(input != null && input.getDates() != null && !input.getDates().isEmpty()) {
            for(Date date: input.getDates()) {
                this.service.persistZappiData(serial, date);
            }
        } else {
            // persist yesterdays data when no date(s) are specified
            Calendar yesterday = Calendar.getInstance();
            yesterday.add(Calendar.DATE, -1);
            this.service.persistZappiData(serial, yesterday.getTime());
        }
    }

    @GetMapping("/zappi/{serial}/{date}")
    public DayOutput getZappiHistoryByDate(
            @PathVariable("serial") String serial,
            @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        HistoryEntity historicData = this.service.findHistoricData(serial, date);
        return DayOutput.builder()
                .date(date)
                .serial(serial)
                .charged(historicData.getCharged())
                .generated(historicData.getGenerated())
                .consumed(historicData.getConsumed())
                .exported(historicData.getExported())
                .imported(historicData.getImported())
                .build();
    }

    @GetMapping("/zappi/{serial}/{start}/{end}")
    public HistoryOutput getZappiHistoryInRange(
            @PathVariable("serial") String serial,
            @PathVariable("start") @DateTimeFormat(pattern = "yyyy-MM-dd") Date start,
            @PathVariable("end") @DateTimeFormat(pattern = "yyyy-MM-dd") Date end
    ) {
        List<DayOutput> historicData = this.service.findHistoricData(serial, start, end).stream().map(e ->
                DayOutput.builder()
                        .date(e.getId().getDate())
                        .serial(e.getId().getSerial())
                        .charged(e.getCharged())
                        .generated(e.getGenerated())
                        .consumed(e.getConsumed())
                        .exported(e.getExported())
                        .imported(e.getImported())
                        .build()
        ).collect(Collectors.toList());

        return HistoryOutput.builder()
                .days(historicData)
                .build();
    }
}
