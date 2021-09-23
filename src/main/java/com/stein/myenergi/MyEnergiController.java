package com.stein.myenergi;

import com.stein.myenergi.api.dto.HistoryDay;
import com.stein.myenergi.api.dto.Zappi;
import com.stein.myenergi.database.HistoryRepository;
import com.stein.myenergi.database.entities.HistoryEntity;
import com.stein.myenergi.database.entities.ZappiEntity;
import com.stein.myenergi.service.MyEnergiApiService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Calendar;

@RestController
@CrossOrigin
public class MyEnergiController {

    private final ModelMapper modelMapper;
    private final MyEnergiApiService apiService;
    private final HistoryRepository historyRepository;

    @Autowired
    public MyEnergiController(
            ModelMapper modelMapper,
            MyEnergiApiService apiService,
            HistoryRepository historyRepository
    ) {
        this.modelMapper = modelMapper;
        this.apiService = apiService;
        this.historyRepository = historyRepository;
    }

    @GetMapping("/zappi")
    public Zappi[] getAllZappiStatus() {
        return this.apiService.getZappiStatus(null).getZappi();
    }

    @GetMapping("/zappi/{serial}")
    public Zappi getSpecificZappi(@PathVariable("serial") String serial) {
        return this.apiService.getZappiStatus(serial).getZappi()[0];
    }

    @GetMapping("/zappi/{serial}/{year}/{month}/{day}")
    public HistoryEntity getZappiHistoryByDate(
            @PathVariable("serial") String serial,
            @PathVariable("year") @Min(2010) int year,
            @PathVariable("month") @Min(1) @Max(12) int month,
            @PathVariable("day")  @Min(1) @Max(31) int day) {
        // month is 0 based so reduce with 1
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);

        return this.historyRepository.findById(calendar.getTime()).orElseGet(() -> {
            HistoryDay[] zappiHistory = this.apiService.getZappiHistory(serial, calendar);
            HistoryEntity entity = modelMapper.map(zappiHistory, HistoryEntity.class);
            entity.setDate(calendar.getTime());
            entity.setZappi(new ZappiEntity(serial));
            return entity;
        });
    }
}
