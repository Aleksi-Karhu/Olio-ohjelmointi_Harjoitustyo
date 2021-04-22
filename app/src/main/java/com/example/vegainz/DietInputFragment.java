package com.example.vegainz;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController navController = Navigation.findNavController(view);


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

    public void checkDiet(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = v.findViewById(radioId);

    }



   

}