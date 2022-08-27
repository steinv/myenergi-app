package com.stein.myenergi.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Zappi {
    @JsonProperty("che") private int chargeAddedInKWh;
    // counts 1 - 10 when command sent, then 254 - success, 253 - failure, 255 - never received any comamnds
    @JsonProperty("cmt") private int commandTimer;
    @JsonProperty("dat") private String date;
    // does not appear when zero, set default
    @JsonProperty("div") private int diversionAmountWatts = 0;
    @JsonProperty("dst") private int useDaylightSavingsTime;
    @JsonProperty("ectp1") private int ct1Watts;
    @JsonProperty("ectp2") private int ct2Watts;
    @JsonProperty("ectp3") private int ct3Watts;
    @JsonProperty("ectp4") private int ct4Watts;
    @JsonProperty("ectp5") private int ct5Watts;
    @JsonProperty("ectp6") private int ct6Watts;
    @JsonProperty("ectt1") private String ct1Name;
    @JsonProperty("ectt2") private String ct2Name;
    @JsonProperty("ectt3") private String ct3Name;
    @JsonProperty("ectt4") private String ct4Name;
    @JsonProperty("ectt5") private String ct5Name;
    @JsonProperty("ectt6") private String ct6Name;
    @JsonProperty("frq") private int supplyFrequency;
    @JsonProperty("fwv") private String firmwareVersion;
    @JsonProperty("gen") private int generatedWatts;		//Generated Watts
    @JsonProperty("grd") private int wattsFromGrid;
    // Lock Status (4 bits ) private int 1st digit - ? ) private int 2nd digit - 1 unlocked, 0 locked)
    @JsonProperty("lck") private int lock;
    @JsonProperty("mgl") private int minimumGreenLevel;
    @JsonProperty("pha") private int phases;
    @JsonProperty("pri") private int priority;
    //Status A=EV Disconnected, B1=EV Connected, B2=Waiting for EV, C1=EV Ready to Charge, C2= Charging, F= Fault
    @JsonProperty("pst") private String status;
    @JsonProperty("sbh") private int smartBoostStartTimeHour;
    @JsonProperty("sbk") private int smartBoostKWhToAdd;
    @JsonProperty("sbm") private int smartBoostStartTimeMinute;
    @JsonProperty("sno") private String serialNumber;
    // Status  1=Paused 3=Diverting/Charging 5=Complete
    @JsonProperty("sta") private int chargingStatus;
    @JsonProperty("tbh") private int boostHour;
    //- Note charge remaining for boost = tbk-che
    @JsonProperty("tbk") private int boostKWh;
    @JsonProperty("tbm") private int boostMinutes;
    @JsonProperty("tim") private String time;
    @JsonProperty("vol") private int supplyVoltage;
    //Zappi Mode - 1=Fast, 2=Eco, 3=Eco+, 4=Stopped
    @JsonProperty("zmo") private int zappiMode;

    // default constructor 
    public Zappi() {}
    
    @Override
    public String toString() {
        return "Zappi{" +
                "chargeAddedInKWh=" + chargeAddedInKWh +
                ", commandTimer=" + commandTimer +
                ", date='" + date + '\'' +
                ", diversionAmountWatts=" + diversionAmountWatts +
                ", useDaylightSavingsTime=" + useDaylightSavingsTime +
                ", ct1Watts=" + ct1Watts +
                ", ct2Watts=" + ct2Watts +
                ", ct3Watts=" + ct3Watts +
                ", ct4Watts=" + ct4Watts +
                ", ct5Watts=" + ct5Watts +
                ", ct6Watts=" + ct6Watts +
                ", ct1Name='" + ct1Name + '\'' +
                ", ct2Name='" + ct2Name + '\'' +
                ", ct3Name='" + ct3Name + '\'' +
                ", ct4Name='" + ct4Name + '\'' +
                ", ct5Name='" + ct5Name + '\'' +
                ", ct6Name='" + ct6Name + '\'' +
                ", supplyFrequency=" + supplyFrequency +
                ", firmwareVersion='" + firmwareVersion + '\'' +
                ", generatedWatts=" + generatedWatts +
                ", wattsFromGrid=" + wattsFromGrid +
                ", lock=" + lock +
                ", minimumGreenLevel=" + minimumGreenLevel +
                ", phases=" + phases +
                ", priority=" + priority +
                ", status='" + status + '\'' +
                ", smartBoostStartTimeHour=" + smartBoostStartTimeHour +
                ", smartBoostKWhToAdd=" + smartBoostKWhToAdd +
                ", smartBoostStartTimeMinute=" + smartBoostStartTimeMinute +
                ", serialNumber=" + serialNumber +
                ", chargingStatus=" + chargingStatus +
                ", boostHour=" + boostHour +
                ", boostKWh=" + boostKWh +
                ", boostMinutes=" + boostMinutes +
                ", time='" + time + '\'' +
                ", supplyVoltage=" + supplyVoltage +
                ", zappiMode=" + zappiMode +
                '}';
    }

    public int getChargeAddedInKWh() {
        return chargeAddedInKWh;
    }

    public void setChargeAddedInKWh(int chargeAddedInKWh) {
        this.chargeAddedInKWh = chargeAddedInKWh;
    }

    public int getCommandTimer() {
        return commandTimer;
    }

    public void setCommandTimer(int commandTimer) {
        this.commandTimer = commandTimer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDiversionAmountWatts() {
        return diversionAmountWatts;
    }

    public void setDiversionAmountWatts(int diversionAmountWatts) {
        this.diversionAmountWatts = diversionAmountWatts;
    }

    public int getUseDaylightSavingsTime() {
        return useDaylightSavingsTime;
    }

    public void setUseDaylightSavingsTime(int useDaylightSavingsTime) {
        this.useDaylightSavingsTime = useDaylightSavingsTime;
    }

    public int getCt1Watts() {
        return ct1Watts;
    }

    public void setCt1Watts(int ct1Watts) {
        this.ct1Watts = ct1Watts;
    }

    public int getCt2Watts() {
        return ct2Watts;
    }

    public void setCt2Watts(int ct2Watts) {
        this.ct2Watts = ct2Watts;
    }

    public int getCt3Watts() {
        return ct3Watts;
    }

    public void setCt3Watts(int ct3Watts) {
        this.ct3Watts = ct3Watts;
    }

    public int getCt4Watts() {
        return ct4Watts;
    }

    public void setCt4Watts(int ct4Watts) {
        this.ct4Watts = ct4Watts;
    }

    public int getCt5Watts() {
        return ct5Watts;
    }

    public void setCt5Watts(int ct5Watts) {
        this.ct5Watts = ct5Watts;
    }

    public int getCt6Watts() {
        return ct6Watts;
    }

    public void setCt6Watts(int ct6Watts) {
        this.ct6Watts = ct6Watts;
    }

    public String getCt1Name() {
        return ct1Name;
    }

    public void setCt1Name(String ct1Name) {
        this.ct1Name = ct1Name;
    }

    public String getCt2Name() {
        return ct2Name;
    }

    public void setCt2Name(String ct2Name) {
        this.ct2Name = ct2Name;
    }

    public String getCt3Name() {
        return ct3Name;
    }

    public void setCt3Name(String ct3Name) {
        this.ct3Name = ct3Name;
    }

    public String getCt4Name() {
        return ct4Name;
    }

    public void setCt4Name(String ct4Name) {
        this.ct4Name = ct4Name;
    }

    public String getCt5Name() {
        return ct5Name;
    }

    public void setCt5Name(String ct5Name) {
        this.ct5Name = ct5Name;
    }

    public String getCt6Name() {
        return ct6Name;
    }

    public void setCt6Name(String ct6Name) {
        this.ct6Name = ct6Name;
    }

    public int getSupplyFrequency() {
        return supplyFrequency;
    }

    public void setSupplyFrequency(int supplyFrequency) {
        this.supplyFrequency = supplyFrequency;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    public int getGeneratedWatts() {
        return generatedWatts;
    }

    public void setGeneratedWatts(int generatedWatts) {
        this.generatedWatts = generatedWatts;
    }

    public int getWattsFromGrid() {
        return wattsFromGrid;
    }

    public void setWattsFromGrid(int wattsFromGrid) {
        this.wattsFromGrid = wattsFromGrid;
    }

    public int getLock() {
        return lock;
    }

    public void setLock(int lock) {
        this.lock = lock;
    }

    public int getMinimumGreenLevel() {
        return minimumGreenLevel;
    }

    public void setMinimumGreenLevel(int minimumGreenLevel) {
        this.minimumGreenLevel = minimumGreenLevel;
    }

    public int getPhases() {
        return phases;
    }

    public void setPhases(int phases) {
        this.phases = phases;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSmartBoostStartTimeHour() {
        return smartBoostStartTimeHour;
    }

    public void setSmartBoostStartTimeHour(int smartBoostStartTimeHour) {
        this.smartBoostStartTimeHour = smartBoostStartTimeHour;
    }

    public int getSmartBoostKWhToAdd() {
        return smartBoostKWhToAdd;
    }

    public void setSmartBoostKWhToAdd(int smartBoostKWhToAdd) {
        this.smartBoostKWhToAdd = smartBoostKWhToAdd;
    }

    public int getSmartBoostStartTimeMinute() {
        return smartBoostStartTimeMinute;
    }

    public void setSmartBoostStartTimeMinute(int smartBoostStartTimeMinute) {
        this.smartBoostStartTimeMinute = smartBoostStartTimeMinute;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getChargingStatus() {
        return chargingStatus;
    }

    public void setChargingStatus(int chargingStatus) {
        this.chargingStatus = chargingStatus;
    }

    public int getBoostHour() {
        return boostHour;
    }

    public void setBoostHour(int boostHour) {
        this.boostHour = boostHour;
    }

    public int getBoostKWh() {
        return boostKWh;
    }

    public void setBoostKWh(int boostKWh) {
        this.boostKWh = boostKWh;
    }

    public int getBoostMinutes() {
        return boostMinutes;
    }

    public void setBoostMinutes(int boostMinutes) {
        this.boostMinutes = boostMinutes;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getSupplyVoltage() {
        return supplyVoltage;
    }

    public void setSupplyVoltage(int supplyVoltage) {
        this.supplyVoltage = supplyVoltage;
    }

    public int getZappiMode() {
        return zappiMode;
    }

    public void setZappiMode(int zappiMode) {
        this.zappiMode = zappiMode;
    }
}
