package com.stein.myenergi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PersistInput {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private List<Date> dates;

    public PersistInput() {}
    
    public PersistInput(List<Date> dates) {
        this.dates = dates;
    }

    public List<Date> getDates() {
        return dates;
    }

    public void setDates(List<Date> dates) {
        this.dates = dates;
    }


}
