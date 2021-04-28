package com.example.vegainz;

import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

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
    private CollectionReference dietRef = db.collection("dietEntries");
    private CollectionReference carbonRef = db.collection("carbonEntries");

    public void makeMassEntry(String date, float mass) throws ParseException {
        MassEntry ME = new MassEntry(date, mass);

        massRef.add(ME).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d(TAG, "Saving entry successful ");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "Saving entry failed");
            }
        });

    }



    public void createMassEntry(String date, float mass) throws ParseException {
        Map<String, Object> saveMass = new HashMap<String, Object>();
        //saveMass.put(DATE_KEY, date);
        //saveMass.put(MASS_KEY, mass);
        CO2Request request = new CO2Request();
        request.getData();

        entries.add(new MassEntry(date,mass));
    }

    public void getMassEntries() {
        massRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    MassEntry ME = documentSnapshot.toObject(MassEntry.class);

                    String date = ME.getDate();
                    float mass = ME.getMass();

                    System.out.println("Date: "+ date + "\nMass: "+ mass + "\n\n");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "Failure fetching data");
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
                Log.d(TAG, "Saving entry successful ");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "Saving entry failed");
            }
        });

    }

    public void getDietEntries() {
        dietRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
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

                    System.out.println("Date: "+ date + "\nBeef: "+ beef + "\n\n");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "Failure fetching data");
            }
        });
    }


    public void createCOEntry(String date, float carbon) throws ParseException {


    }




    public ArrayList<com.github.mikephil.charting.data.Entry> getMassEntry() throws ParseException {
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
