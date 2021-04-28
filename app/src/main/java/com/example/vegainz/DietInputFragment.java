package com.example.vegainz;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DietInputFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DietInputFragment extends Fragment {

    EditText date;
    CheckBox lowCarbon;
    EditText beef;
    EditText fish;
    EditText pork;
    EditText rice;
    EditText egg;
    EditText dairy;
    EditText cheese;
    RadioGroup radioGroup;
    RadioButton radioButton;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DietInputFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DietInputFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DietInputFragment newInstance(String param1, String param2) {
        DietInputFragment fragment = new DietInputFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_diet_input, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @NonNull Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController navController = Navigation.findNavController(view);
        final EntryController entryController = new EntryController();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH).withResolverStyle(ResolverStyle.STRICT);
        final DateValidator validator = new DateValidatorUsingDateTimeFormatter(dateFormatter);

        date = view.findViewById(R.id.editTextDate);
        Button submit = view.findViewById(R.id.submitButton);
        lowCarbon = view.findViewById(R.id.lowCarbonCheckBox);
        beef = view.findViewById(R.id.beefInput);
        fish = view.findViewById(R.id.fishInput);
        pork = view.findViewById(R.id.porkInput);
        rice = view.findViewById(R.id.riceInput);
        egg = view.findViewById(R.id.eggInput);
        dairy = view.findViewById(R.id.dairyInput);
        cheese = view.findViewById(R.id.cheeseInput);
        radioGroup = view.findViewById(R.id.dietRadioGroup);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (date.getText().toString().isEmpty() || beef.getText().toString().isEmpty() || fish.getText().toString().isEmpty() || pork.getText().toString().isEmpty() || rice.getText().toString().isEmpty() ||
                        egg.getText().toString().isEmpty() || dairy.getText().toString().isEmpty() || cheese.getText().toString().isEmpty() || validator.isValid(date.getText().toString()) == false){
                    // lisää popup ilmoitus puuttuvista arvoista. sama massinputtiin
                    System.out.println("false");
                }else {
                    try {
                        entryController.createFoodCalculationEntry(date.getText().toString(), onRadioButtonClicked(view), lowCarbon.isChecked(), Float.valueOf(beef.getText().toString()), Float.valueOf(fish.getText().toString()), Float.valueOf(pork.getText().toString()), Float.valueOf(dairy.getText().toString()),
                                 Float.valueOf(cheese.getText().toString()), Float.valueOf(rice.getText().toString()), Integer.parseInt(egg.getText().toString()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        });



        Button DIHome = view.findViewById(R.id.buttonDItoHome);
        DIHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_dietInputFragment_to_homeFragment);
            }
        });


    }

    public String onRadioButtonClicked(View view) {
        RadioButton omni = view.findViewById(R.id.omnivoreButton);
        RadioButton vegan = view.findViewById(R.id.veganButton);
        RadioButton vegetarian = view.findViewById(R.id.vegetarianButton);
        String diet = "";

        if ( omni.isChecked() == true ) {
            diet = "Omnivore";
        }

        if ( vegan.isChecked() == true ){
            diet = "Vegan";
        }
        if ( vegetarian.isChecked() == true ){
            diet = "Vegetarian";
        }
        return diet;
    }

}