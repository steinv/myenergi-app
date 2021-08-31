package com.stein.myenergi.api.calls.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NoInput implements MyenergiCallInput {
    @JsonIgnoreProperties
    public static final NoInput INSTANCE = new NoInput();

    @Override
    public Object[] getParameters() {
        return null;
    }
}
