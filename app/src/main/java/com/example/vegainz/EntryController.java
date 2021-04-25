package com.example.vegainz;

import java.util.ArrayList;

public class EntryController {
    private final ArrayList<Entry> entries = new ArrayList();

    public void createMassEntry(String date, float mass){
        entries.add(new MassEntry(date,mass));
    }

    public void printMassEntries(){
        System.out.println("All accounts:");
        Entry temp;
        if(entries != null){
            for(int i = 0;i<entries.size();i++) {
                temp = entries.get(i);
                System.out.println("Date: "+temp.date+" Mass: "+ ((MassEntry) temp).mass);
                }
            }
    }


}
