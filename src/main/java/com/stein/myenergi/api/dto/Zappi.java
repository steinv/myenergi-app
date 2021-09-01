package com.stein.myenergi.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
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
    @JsonProperty("sno") private int serialNumber;
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
}
