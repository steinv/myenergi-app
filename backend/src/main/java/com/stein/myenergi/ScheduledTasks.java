package com.stein.myenergi;

import com.stein.myenergi.api.dto.Zappi;
import com.stein.myenergi.service.MyEnergiApiService;
import com.stein.myenergi.service.MyEnergiService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
@RequiredArgsConstructor
/**
 * If you are hosting this application on heroku, like I am doing, these scheduleTasks will not work
 * Heroku puts your application to sleep after 30 minutes of inactivity.
 *
 * As an alternative you can use Google Cloud scheduler
 * https://console.cloud.google.com/cloudscheduler/jobs/new
 */
public class ScheduledTasks {

    private final MyEnergiApiService apiService;
    private final MyEnergiService service;

    /**
     * Daily, at 6am, persists historic data of your zappi device(s)
     */
    @Scheduled(cron = "0 0 6 * * *")
    public void persistHistoricZappiData() {
        Zappi[] zappis = this.apiService.getZappiStatus(null).getZappi();
        Date[] dates = this.datesToFetch();

        for(Date date: dates) {
            for (Zappi zappi : zappis) {
                this.service.persistZappiData(zappi.getSerialNumber(), date);
            }
        }
    }

    // TODO determine which date(s) to fetch
    private Date[] datesToFetch() {
        Calendar yesterday = Calendar.getInstance();
        yesterday.add(Calendar.DATE, -1);

        return new Date[]{ yesterday.getTime() };
    }
}
