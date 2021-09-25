package com.stein.myenergi.dto;

import lombok.Data;

import java.util.Calendar;
import java.util.List;

@Data
public class PersistInput {
    private List<Calendar> dates;
}
