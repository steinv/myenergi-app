package com.stein.myenergi;

public enum Device {
    EDDI("E"),
    HARVY("H"),
    ZAPPI("Z"),
    WILDCARD("*"),

    ;

    private final String code;

    Device(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return this.code;
    }
}
