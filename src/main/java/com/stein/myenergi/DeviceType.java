package com.stein.myenergi;

public enum DeviceType {
    EDDI("E"),
    HARVY("H"),
    ZAPPI("Z"),
    WILDCARD("*"),

    ;

    private final String code;

    DeviceType(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return this.code;
    }
}
