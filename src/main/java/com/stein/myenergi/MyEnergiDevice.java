package com.stein.myenergi;

public enum MyEnergiDevice {
    EDDI("E"),
    HARVY("H"),
    ZAPPI("Z"),
    WILDCARD("*"),

    ;

    private final String code;

    MyEnergiDevice(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return this.code;
    }
}
