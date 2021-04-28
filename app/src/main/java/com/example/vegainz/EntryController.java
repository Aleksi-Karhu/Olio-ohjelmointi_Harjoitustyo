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
    private final static ArrayList<Entry> carbonList = new ArrayList();

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
        /*This method creates mass entries and sent them to Firestore database*/
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
        /*This method gets mass entries from Firestore database and saves them to arraylist*/
        massRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                massList.clear();
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    MassEntry ME = documentSnapshot.toObject(MassEntry.class);

                    String date = ME.getDate();
                    float mass = ME.getMass();
                    massList.add(new MassEntry(date,mass));
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
        /*This method gets CO2 entries from Firestore database and saves them to arraylist*/
        carbonRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                carbonList.clear();
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    CO2Entry CE = documentSnapshot.toObject(CO2Entry.class);

                    String date = CE.getDate();
                    double carbon = CE.getCarbon();
                    carbonList.add(new CO2Entry(date,carbon));
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
        /*This method creates foodcalculation entries and sent them to Firestore database*/
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
        /*This method gets foodcalculation entries from Firestore database and saves them to arraylist*/
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
        /*This method creates CO2 entries and sent them to Firestore database*/
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
        /*This method sorts massList by date and uses it to create Arraylist<com.github.mikephil.charting.data.Entry> to use with MPAndroidChart*/
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        ArrayList<com.github.mikephil.charting.data.Entry> tempList = new ArrayList<>();
        Entry temp;

        if(massList != null){
            //Sorts massList by date
            Collections.sort(massList, Comparator.comparing(Entry::getTimeDate));
            for(int i = 0;i<massList.size();i++) {

                temp = massList.get(i);
                /*1 hour in milliseconds is added to temp.date to fight rounding errors caused by float*/
                tempList.add(new com.github.mikephil.charting.data.Entry((float)sdf.parse(temp.date).getTime()+3600000,((MassEntry)temp).mass));
            }
        }else{
            System.out.println("404 ERROR MASS ENTRIES NOT FOUND");
        }
        return tempList;
    }

    public ArrayList<com.github.mikephil.charting.data.Entry> createCO2GraphEntries() throws ParseException {
        /*This method sorts carbonList by date and uses it to create Arraylist<com.github.mikephil.charting.data.Entry> to use with MPAndroidChart*/
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        ArrayList<com.github.mikephil.charting.data.Entry> tempList = new ArrayList<>();
        Entry temp;

        if(carbonList != null){
            //Sorts carbonList by date
            Collections.sort(carbonList, Comparator.comparing(Entry::getTimeDate));

            for(int i = 0;i<carbonList.size();i++) {

                temp = carbonList.get(i);
                tempList.add(new com.github.mikephil.charting.data.Entry((float)(sdf.parse(temp.date).getTime()+3600000), (float) (((CO2Entry)temp).carbon)));
            }
        }else{
            System.out.println("404 ERROR CO2 ENTRIES NOT FOUND");
        }
        return tempList;
    }

    public ArrayList<com.github.mikephil.charting.data.Entry> createMeatGraphEntries() throws ParseException {
        /*This method sorts foodList by date and uses it to create Arraylist<com.github.mikephil.charting.data.Entry> to use with MPAndroidChart*/
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        ArrayList<com.github.mikephil.charting.data.Entry> tempList = new ArrayList<>();
        Entry temp;
        float meatComsumption;
        if(foodList != null){
            //Sorts foodList by date
            Collections.sort(foodList, Comparator.comparing(Entry::getTimeDate));

            for(int i = 0;i<foodList.size();i++) {

                temp = foodList.get(i);
                meatComsumption = (((FoodCalculationEntry)temp).beef+((FoodCalculationEntry)temp).pork);
                tempList.add(new com.github.mikephil.charting.data.Entry((float)(sdf.parse(temp.date).getTime()+3600000), (float) meatComsumption));
            }
        }else{
            System.out.println("404 ERROR FOODCALCUTALITON ENTRIES NOT FOUND");
        }
        return tempList;
    }
}
