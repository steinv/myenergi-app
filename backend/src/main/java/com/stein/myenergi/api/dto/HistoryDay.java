package com.stein.myenergi.api.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HistoryDay implements Serializable {

    // TODO fix InvalidClassException: com.stein.myenergi.api.dto.HistoryDay; local class incompatible: stream classdesc serialVersionUID = -4451134410242322623, local class serialVersionUID = 1
    private static final long serialVersionUID = -4451134410242322623L;
    
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
    // Imported Joules this minute; divide by 60 get average Watts; divide by
    // 3600000 to get kWh
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

    // default constructor
    public HistoryDay() {
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getImportedJoules() {
        return importedJoules;
    }

    public void setImportedJoules(int importedJoules) {
        this.importedJoules = importedJoules;
    }

    public int getExportedJoules() {
        return exportedJoules;
    }

    public void setExportedJoules(int exportedJoules) {
        this.exportedJoules = exportedJoules;
    }

    public int getGenerated() {
        return generated;
    }

    public void setGenerated(int generated) {
        this.generated = generated;
    }

    public int getGeneratedJoules() {
        return generatedJoules;
    }

    public void setGeneratedJoules(int generatedJoules) {
        this.generatedJoules = generatedJoules;
    }

    public int getPhase1JoulesForCharging() {
        return phase1JoulesForCharging;
    }

    public void setPhase1JoulesForCharging(int phase1JoulesForCharging) {
        this.phase1JoulesForCharging = phase1JoulesForCharging;
    }

    public int getPhase2JoulesForCharging() {
        return phase2JoulesForCharging;
    }

    public void setPhase2JoulesForCharging(int phase2JoulesForCharging) {
        this.phase2JoulesForCharging = phase2JoulesForCharging;
    }

    public int getPhase3JoulesForCharging() {
        return phase3JoulesForCharging;
    }

    public void setPhase3JoulesForCharging(int phase3JoulesForCharging) {
        this.phase3JoulesForCharging = phase3JoulesForCharging;
    }

    public int getVoltage() {
        return voltage;
    }

    public void setVoltage(int voltage) {
        this.voltage = voltage;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getImportedJoulesCt1() {
        return importedJoulesCt1;
    }

    public void setImportedJoulesCt1(int importedJoulesCt1) {
        this.importedJoulesCt1 = importedJoulesCt1;
    }

    public int getImportedJoulesCt2() {
        return importedJoulesCt2;
    }

    public void setImportedJoulesCt2(int importedJoulesCt2) {
        this.importedJoulesCt2 = importedJoulesCt2;
    }
}
