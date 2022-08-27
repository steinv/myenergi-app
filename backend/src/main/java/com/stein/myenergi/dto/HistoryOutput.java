package com.stein.myenergi.dto;

import java.util.List;

public class HistoryOutput {

    private List<DayOutput> days;

    public HistoryOutput() {}
    
    public HistoryOutput(List<DayOutput> days) {
        this.days = days;
    }

    public List<DayOutput> getDays() {
        return days;
    }

    public void setDays(List<DayOutput> days) {
        this.days = days;
    }
}
