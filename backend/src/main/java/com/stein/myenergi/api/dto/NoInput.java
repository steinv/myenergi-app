package com.stein.myenergi.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class NoInput implements MyenergiCallInput {
    @JsonIgnoreProperties
    public static final NoInput INSTANCE = new NoInput();

    public NoInput() {}
    
    @Override
    public Object[] getParameters() {
        return null;
    }
}
