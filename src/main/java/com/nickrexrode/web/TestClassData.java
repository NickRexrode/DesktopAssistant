package com.nickrexrode.web;

import java.util.List;

public final class TestClassData {
    private String date;
    private String localName;
    private String name;
    private String countryCode;
    private boolean fixed;
    private boolean global;
    private List<String> counties;
    private int launchYear;
    private List<String> types;

    @Override
    public String toString() {
        return "TestClassData{" +
                "date='" + date + '\'' +
                ", localName='" + localName + '\'' +
                ", name='" + name + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", fixed=" + fixed +
                ", global=" + global +
                ", counties=" + counties +
                ", launchYear=" + launchYear +
                ", types=" + types +
                '}';
    }
}


