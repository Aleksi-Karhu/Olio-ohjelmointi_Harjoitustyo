package com.example.vegainz;

public class CO2Entry extends Entry {

    double carbon;

    public CO2Entry() {

    }

    public CO2Entry(String date, double carbon) {
        super(date);
        this.carbon = carbon;

    }

    public double getCarbon() {
        return carbon;
    }
}
