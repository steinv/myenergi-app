package com.stein.myenergi.api.calls.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ZappiStatusCallInput implements MyenergiCallInput {
    private String serial;

    @Override
    public Object[] getParameters() {
        return null;
    }
}
