package com.example.vegainz;

import android.os.StrictMode;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import androidx.annotation.NonNull;

public class EntryController {
    private final static ArrayList<Entry> foodList = new ArrayList();
    private final static ArrayList<Entry> massList = new ArrayList();
    private final static ArrayList<CO2Entry> carbonList = new ArrayList();

    private static final String DATE_KEY = "date";
    private static final String MASS_KEY = "mass";
    private static final String MASS_CREATE_TAG = "Mass create";
    private static final String MASS_FETCH_TAG = "Mass fetch";
    private static final String DIET_CREATE_TAG = "Diet create";
    private static final String DIET_FETCH_TAG = "Diet fetch";
    private static final String CARBON_CREATE_TAG = "Carbon create";
    private static final String CARBON_FETCH_TAG = "Carbon fetch";

    private DocumentReference mDocRef = FirebaseFirestore.getInstance().document("massEntries/userMass");
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference massRef = db.collection("massEntries");
    private CollectionReference dietRef = db.collection("dietEntries");
    private CollectionReference carbonRef = db.collection("carbonEntries");

    public void createMassEntry(String date, float mass) throws ParseException {
        MassEntry ME = new MassEntry(date, mass);

        massRef.add(ME).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d(MASS_CREATE_TAG, "Saving entry successful ");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(MASS_CREATE_TAG, "Saving entry failed");
            }
        });

    }


    public void getMassEntries() {
        massRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                massList.clear();
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    MassEntry ME = documentSnapshot.toObject(MassEntry.class);

                    String date = ME.getDate();
                    float mass = ME.getMass();
                    massList.add(new MassEntry(date,mass));
                    System.out.println("Date: "+ date + "\nMass: "+ mass + "\n\n");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(MASS_FETCH_TAG, "Failure fetching data");
            }
        });
    }

    public void getCO2Entries() {
        carbonRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                carbonList.clear();
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    CO2Entry CE = documentSnapshot.toObject(CO2Entry.class);

                    String date = CE.getDate();
                    double carbon = CE.getCarbon();
                    carbonList.add(new CO2Entry(date,carbon));
                    System.out.println("Date: "+ date + "\nCarbon: "+ carbon + "\n\n");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(CARBON_FETCH_TAG, "Failure fetching data");
            }
        });
    }



    public void createFoodCalculationEntry(String date, String diet, boolean lowCarbonPreference,
                                           float beef, float fish, float pork, float dairy,
                                           float cheese, float rice, int eggs) throws ParseException {

        FoodCalculationEntry FCE = new FoodCalculationEntry(date, diet, lowCarbonPreference, beef,
                fish, pork, dairy, cheese, rice, eggs);

        dietRef.add(FCE).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d(DIET_CREATE_TAG, "Saving entry successful ");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(DIET_CREATE_TAG, "Saving entry failed");
            }
        });

    }

    public void getDietEntries() {
        dietRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                foodList.clear();
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    FoodCalculationEntry FCE = documentSnapshot.toObject(FoodCalculationEntry.class);

                    String date = FCE.getDate();
                    String diet = FCE.getDiet();
                    boolean lowCarbonPreference = FCE.isLowCarbonPreference();
                    float beef = FCE.getBeef();
                    float fish = FCE.getFish();
                    float pork = FCE.getPork();
                    float dairy = FCE.getDairy();
                    float cheese = FCE.getCheese();
                    float rice = FCE.getRice();
                    int eggs = FCE.getEggs();
                    foodList.add(new FoodCalculationEntry( date, diet, lowCarbonPreference, beef, fish, pork, dairy, cheese, rice, eggs));
                    System.out.println("Date: "+ date + "\nBeef: "+ beef + "\n\n");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(DIET_FETCH_TAG, "Failure fetching data");
            }
        });
    }


    public void createCOEntry(String date, double carbon) throws ParseException {

        CO2Entry CE = new CO2Entry(date, carbon);

        carbonRef.add(CE).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d(CARBON_CREATE_TAG, "Saving entry successful ");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(CARBON_CREATE_TAG, "Saving entry failed");
            }
        });

    }

    public ArrayList<com.github.mikephil.charting.data.Entry> createMassGraphEntries() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        ArrayList<com.github.mikephil.charting.data.Entry> tempList = new ArrayList<>();
        Entry temp;

        if(massList != null){
            Collections.sort(massList, Comparator.comparing(Entry::getTimeDate));
            for(int i = 0;i<massList.size();i++) {

                temp = massList.get(i);
                tempList.add(new com.github.mikephil.charting.data.Entry((float)sdf.parse(temp.date).getTime()+3600000,((MassEntry)temp).mass));
                System.out.println("Date: "+temp.date+" Mass: "+ ((MassEntry) temp).mass);
            }
        }else{
            System.out.println("Null");
        }
        return tempList;
    }

    public ArrayList<com.github.mikephil.charting.data.Entry> createCO2GraphEntries() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        ArrayList<com.github.mikephil.charting.data.Entry> tempList = new ArrayList<>();
        Entry temp;

        if(carbonList != null){
            Collections.sort(carbonList, Comparator.comparing(Entry::getTimeDate));

            for(int i = 0;i<carbonList.size();i++) {

                temp = carbonList.get(i);
                tempList.add(new com.github.mikephil.charting.data.Entry((float)(sdf.parse(temp.date).getTime()+3600000), (float) (((CO2Entry)temp).carbon)));
                System.out.println("Date: "+temp.date +" GetTime: "+sdf.parse(temp.date).getTime()+" Carbon: "+ ((CO2Entry) temp).carbon);
            }
        }else{
            System.out.println("Null");
        }
        return tempList;
    }

    public ArrayList<com.github.mikephil.charting.data.Entry> createMeatGraphEntries() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        ArrayList<com.github.mikephil.charting.data.Entry> tempList = new ArrayList<>();
        Entry temp;

        if(foodList != null){
            Collections.sort(foodList, Comparator.comparing(Entry::getTimeDate));

            for(int i = 0;i<foodList.size();i++) {

                temp = foodList.get(i);
                tempList.add(new com.github.mikephil.charting.data.Entry((float)(sdf.parse(temp.date).getTime()+3600000), (float) (((FoodCalculationEntry)temp).beef+((FoodCalculationEntry)temp).pork)));
                System.out.println("Date: "+temp.date +" GetTime: "+sdf.parse(temp.date).getTime()+" Total meat: "+  (((FoodCalculationEntry)temp).beef+((FoodCalculationEntry)temp).pork));
            }
        }else{
            System.out.println("Null");
        }
        return tempList;
    }
}
