package com.stein.myenergi.api.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class HistoryDay implements Serializable {

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
    private int generated = 0;
    @JsonProperty("gep")
    private int generatedJoules = 0;
    @JsonProperty("h1d")
    private int phase1JoulesForCharging = 0;
    @JsonProperty("h2d")
    private int phase2JoulesForCharging = 0;
    @JsonProperty("h3d")
    private int phase3JoulesForCharging = 0;
    @JsonProperty("v1")
    private int voltage;
    @JsonProperty("frq")
    private int frequency;
    @JsonProperty("nect1")
    private int importedJoulesCt1;
    @JsonProperty("nect2")
    private int importedJoulesCt2;
}
