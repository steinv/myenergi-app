package com.stein.myenergi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersistInput {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private List<Date> dates;
}
