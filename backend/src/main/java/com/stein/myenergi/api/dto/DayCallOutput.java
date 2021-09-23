package com.stein.myenergi.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.util.HashMap;

@Getter
@JsonIgnoreProperties
public class DayCallOutput extends HashMap<String, HistoryDay[]> implements MyenergiCallOutput {

    public HistoryDay[] getHistoryDay() {
        return this.keySet().stream().findFirst().map((key) -> this.get(key)).orElse(null);
    }
}
