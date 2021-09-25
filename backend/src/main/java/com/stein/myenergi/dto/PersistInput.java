package com.stein.myenergi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Calendar;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersistInput {
    private List<Calendar> dates;
}
