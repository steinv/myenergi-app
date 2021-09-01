package com.stein.myenergi.api.calls.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class HistoryDay {

    // omitted when 0, so set default
    @JsonProperty("min")
    private int minute = 0;
    @JsonProperty("dow")
    private String dayOfWeek;
    @JsonProperty("dom")
    private int dayOfMonth;
    @JsonProperty("mon")
    private int month;
    @JsonProperty("yr")
    private int year;
    // Imported Joules this minute; divide by 60 get average Watts; divide by 3600000 to get kWh
    @JsonProperty("imp")
    private int importedJoules = 0;
    @JsonProperty("exp")
    private int exportedJoules = 0;
    // ???
    @JsonProperty("gen")
    private int generated;
    @JsonProperty("gep")
    private int generatedJoules;
    @JsonProperty("h1d")
    private int phase1JoulesForCharging;
    @JsonProperty("h2d")
    private int phase2JoulesForCharging;
    @JsonProperty("h3d")
    private int phase3JoulesForCharging;
    @JsonProperty("v1")
    private int voltage;
    @JsonProperty("frq")
    private int frequency;
    @JsonProperty("nect1")
    private int importedJoulesCt1;
    @JsonProperty("nect2")
    private int importedJoulesCt2;
}
