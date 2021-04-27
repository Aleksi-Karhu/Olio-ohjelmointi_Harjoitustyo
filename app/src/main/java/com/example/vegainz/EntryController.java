package com.example.vegainz;

import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;

public class EntryController {
    private final static ArrayList<Entry> entries = new ArrayList();

    private static final String DATE_KEY = "date";
    private static final String MASS_KEY = "mass";
    private static final String TAG = "mass";

    private DocumentReference mDocRef = FirebaseFirestore.getInstance().document("massEntries/userMass");
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference massRef = db.collection("massEntries");



    public void makeMassEntry(String date, float mass) throws ParseException {
        MassEntry massentry = new MassEntry(date, mass);

        massRef.add(massentry).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {

            }
        });

    }



    public void createMassEntry(String date, float mass) throws ParseException {
        Map<String, Object> saveMass = new HashMap<String, Object>();
        saveMass.put(DATE_KEY, date);
        saveMass.put(MASS_KEY, mass);
        mDocRef.set(saveMass).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "Saving entry successful ");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "Saving entry failed");
            }
        });

        entries.add(new MassEntry(date,mass));
    }

    public void getMassEntry() {
        mDocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

            }
        });

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
