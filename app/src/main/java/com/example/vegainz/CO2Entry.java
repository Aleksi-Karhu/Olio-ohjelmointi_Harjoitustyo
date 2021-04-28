package com.example.vegainz;

public class CO2Entry extends Entry {

    float carbon;

    public CO2Entry() {

    }

    public CO2Entry(String date, float carbon) {
        super(date);
        this.carbon = carbon;

    }

    public float getCarbon() {
        return carbon;
    }
}
