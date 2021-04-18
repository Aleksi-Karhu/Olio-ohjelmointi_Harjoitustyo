package com.example.vegainz;

public class FoodCalculationEntry extends Entry {

    String diet;
    boolean lowCarbonPreference;
    int beef;
    int fish;
    int pork;
    int dairy;
    int cheese;
    int rice;
    int eggs;

    FoodCalculationEntry(String diet, boolean lowCarbonPreference, int beef, int fish, int pork, int dairy, int cheese, int rice, int eggs, String date) {
        super(date);
        this.diet = diet;
        this.lowCarbonPreference = lowCarbonPreference;
        this.beef = beef;
        this.fish = fish;
        this.pork = pork;
        this.dairy = dairy;
        this.cheese = cheese;
        this.rice = rice;
        this.eggs = eggs;

    }


}
