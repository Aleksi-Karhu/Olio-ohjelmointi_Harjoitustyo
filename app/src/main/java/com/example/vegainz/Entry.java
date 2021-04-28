package com.example.vegainz;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Entry {

    public String date;

    public Entry(){

    }

    public Entry(String date) {

        this.date = date;
    }

    public String getDate(){
        return date;
    }


    public Date getTimeDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date date1 = null;
        try {
            date1 = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date1;
    }

}
