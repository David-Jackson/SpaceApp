package com.wordpress.ayo218.spacenews;

/**
 * Created by Ayo on 1/12/2018.
 */

public class RocketData {
    private String rocket_name;
    private String flight_number;
    private String flight_yeat;

    public RocketData(String rocket_name, String flight_number, String flight_yeat) {
        this.rocket_name = rocket_name;
        this.flight_number = flight_number;
        this.flight_yeat = flight_yeat;
    }

    public String getRocket_name() {
        return rocket_name;
    }

    public String getFlight_number() {
        return flight_number;
    }

    public String getFlight_yeat() {
        return flight_yeat;
    }
}

