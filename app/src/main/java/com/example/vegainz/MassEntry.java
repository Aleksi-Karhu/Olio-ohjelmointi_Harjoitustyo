package com.example.vegainz;

public class MassEntry extends Entry {
    float mass;


    public MassEntry(){

    }

    public MassEntry(String date, float mass) {
        super(date);
        this.mass = mass;

    }

    public float getMass() {
        return mass;
    }

}
