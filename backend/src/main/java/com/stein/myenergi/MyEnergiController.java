package com.stein.myenergi;

import com.stein.myenergi.api.dto.HistoryDay;
import com.stein.myenergi.api.dto.Zappi;
import com.stein.myenergi.database.HistoryRepository;
import com.stein.myenergi.database.entities.HistoryEntity;
import com.stein.myenergi.database.entities.HistoryId;
import com.stein.myenergi.dto.DayOutput;
import com.stein.myenergi.dto.HistoryOutput;
import com.stein.myenergi.dto.PersistInput;
import com.stein.myenergi.service.MyEnergiApiService;
import com.stein.myenergi.service.MyEnergiService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Calendar;
import java.util.Collections;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class MyEnergiController {

    private final ModelMapper modelMapper;
    private final MyEnergiApiService apiService;
    private final MyEnergiService service;
    private final HistoryRepository historyRepository;

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
            for(Calendar date: input.getDates()) {
                this.service.persistZappiData(serial, date);
            }
        } else {
            // persist yesterdays data when no date(s) are specified
            Calendar yesterday = Calendar.getInstance();
            yesterday.add(Calendar.DATE, -1);
            this.service.persistZappiData(serial, yesterday);
        }
    }

    @GetMapping("/zappi/{serial}/{year}/{month}/{day}")
    public DayOutput getZappiHistoryByDate(
            @PathVariable("serial") String serial,
            @PathVariable("year") @Min(2010) int year,
            @PathVariable("month") @Min(1) @Max(12) int month,
            @PathVariable("day")  @Min(1) @Max(31) int day) {
        // month is 0 based so reduce with 1
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);

        HistoryId id = new HistoryId(calendar.getTime(), serial);
        HistoryEntity entity = this.historyRepository.findById(id).orElseGet(() -> {
            HistoryDay[] zappiHistory = this.apiService.getZappiHistory(serial, calendar);
            return modelMapper.map(zappiHistory, HistoryEntity.class);
        });

        return DayOutput.builder()
                .charged(entity.getCharged())
                .generated(entity.getGenerated())
                .consumed(entity.getConsumed())
                .exported(entity.getExported())
                .imported(entity.getImported())
                .build();
    }

    public HistoryOutput getZappiHistoryByDate(
            @PathVariable("serial") String serial,
            @PathVariable("year") @Min(2010) int year,
            @PathVariable("month") @Min(1) @Max(12) int month
    ){
        // month is 0 based so reduce with 1
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1);

        HistoryId id = new HistoryId(calendar.getTime(), serial);
        HistoryEntity entity = this.historyRepository.findById(id).orElseGet(() -> {
            HistoryDay[] zappiHistory = this.apiService.getZappiHistory(serial, calendar);
            return modelMapper.map(zappiHistory, HistoryEntity.class);
        });

        DayOutput.builder()
                .charged(entity.getCharged())
                .generated(entity.getGenerated())
                .consumed(entity.getConsumed())
                .exported(entity.getExported())
                .imported(entity.getImported())
                .build();

        return HistoryOutput.builder()
                .days(Collections.emptyList())
                .build();
    }
}
