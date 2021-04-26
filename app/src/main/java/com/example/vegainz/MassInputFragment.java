package com.example.vegainz;

import android.os.Bundle;
import android.support.annotation.NonNull;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.text.ParseException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MassInputFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MassInputFragment extends Fragment {

    EditText date;
    EditText mass;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MassInputFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MassInputFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MassInputFragment newInstance(String param1, String param2) {
        MassInputFragment fragment = new MassInputFragment();
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
        return inflater.inflate(R.layout.fragment_mass_input, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController navController = Navigation.findNavController(view);
        mass = view.findViewById(R.id.inputMIMass);
        date = view.findViewById(R.id.inputMIDate);
        final Button submit = view.findViewById(R.id.buttonMISubmit);
        Button MIHome = view.findViewById(R.id.buttonMItoHome);
        final EntryController entryController = new EntryController();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (date.getText().toString().isEmpty() || mass.getText().toString().isEmpty()){

                }else {
                    try {
                        entryController.createMassEntry(date.getText().toString(),Float.valueOf(mass.getText().toString()));
                        entryController.getMassEntries();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        });



        MIHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_massInputFragment_to_homeFragment);
            }
        });
    }
}