package com.example.vegainz;

public class FoodCalculationEntry extends Entry {

    String diet;
    boolean lowCarbonPreference;
    float beef;
    float fish;
    float pork;
    float dairy;
    float cheese;
    float rice;
    int eggs;

    public FoodCalculationEntry() {

    }

    public FoodCalculationEntry(String date, String diet, boolean lowCarbonPreference, float beef, float fish, float pork, float dairy, float cheese, float rice, int eggs) {
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

    public String getDiet() {
        return diet;
    }

    public boolean isLowCarbonPreference() {
        return lowCarbonPreference;
    }

    public float getBeef() {
        return beef;
    }

    public float getFish() {
        return fish;
    }

    public float getPork() {
        return pork;
    }

    public float getDairy() {
        return dairy;
    }

    public float getCheese() {
        return cheese;
    }

    public float getRice() {
        return rice;
    }

    public int getEggs() {
        return eggs;
    }
}
