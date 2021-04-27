package com.example.vegainz;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class EntryController {
    private final static ArrayList<Entry> entries = new ArrayList();

    public void createMassEntry(String date, float mass) throws ParseException {

        entries.add(new MassEntry(date,mass));
    }

    public void createFoodCalculationEntry(String diet, boolean lowCarbonPreference,
                                           float beef, float fish, float pork, float dairy,
                                           float cheese, float rice, int eggs, String date) throws ParseException {
        entries.add(new FoodCalculationEntry(diet, lowCarbonPreference, beef, fish, pork, dairy, cheese, rice, eggs, date));
    }

    public void createC0Entry(String date, float mass) throws ParseException {
        entries.add(new CO2Entry(date,mass));
    }




    public ArrayList<com.github.mikephil.charting.data.Entry> getMassEntries() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        ArrayList<com.github.mikephil.charting.data.Entry> tempList = new ArrayList<>();
        Entry temp;
        if(entries != null){
            for(int i = 0;i<entries.size();i++) {
                temp = entries.get(i);
                tempList.add(new com.github.mikephil.charting.data.Entry((float)sdf.parse(temp.date).getTime(),((MassEntry)temp).mass));
                System.out.println("Date: "+temp.date+" Mass: "+ ((MassEntry) temp).mass);
                }
            }
        return tempList;
    }

}
