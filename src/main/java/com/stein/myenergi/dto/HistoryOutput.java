package com.stein.myenergi.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class HistoryOutput {

    private final List<DayOutput> days;
}
