package com.example.vegainz;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MeatChangeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MeatChangeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private LineChart mpLineChart;

    public MeatChangeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MeatChangeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MeatChangeFragment newInstance(String param1, String param2) {
        MeatChangeFragment fragment = new MeatChangeFragment();
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
        return inflater.inflate(R.layout.fragment_meat_change, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController navController = Navigation.findNavController(view);

        Button MeatChangeHome = view.findViewById(R.id.buttonMeatChangetoHome);
        Button MeatChangeRefresh = view.findViewById(R.id.buttonMeatChangeRefresh);

        createMeatGraph(view);

        MeatChangeRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createMeatGraph(view);

            }
        });

        MeatChangeHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_meatChangeFragment_to_homeFragment);
            }
        });
    }

    private ArrayList<com.github.mikephil.charting.data.Entry> dataValuesMeat() throws ParseException {
        EntryController entryController = new EntryController();
        entryController.getDietEntries();
        ArrayList<com.github.mikephil.charting.data.Entry> dataValsMeat = entryController.createMeatGraphEntries();

        return dataValsMeat;
    }

    private void  createMeatGraph( View view){

        mpLineChart = view.findViewById(R.id.lineChartMeatChange);
        XAxis xAxis = mpLineChart.getXAxis();
        YAxis yAxisLeft = mpLineChart.getAxisLeft();
        YAxis yAxisRight = mpLineChart.getAxisRight();

        LineDataSet lineDataSet1 = null;
        try {
            lineDataSet1 = new LineDataSet(dataValuesMeat(), "Meat comsumption per week");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet1);

        lineDataSet1.setColor(Color.RED);
        lineDataSet1.setCircleColor(Color.RED);


        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        xAxis.setValueFormatter(new MyMeatChangeAxisValueFormatter());
        try {
            xAxis.setLabelCount(dataValuesMeat().size(),true);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        LineData data = new LineData(dataSets);
        mpLineChart.setData(data);
        mpLineChart.notifyDataSetChanged();
        mpLineChart.invalidate();
    }




    private class MyMeatChangeAxisValueFormatter extends ValueFormatter {
    // Formatting x-axel in graph to show number of week
    @Override
    public String getFormattedValue(float value) {
        SimpleDateFormat sdf = new SimpleDateFormat("ww");

        return sdf.format(value);
    }
    }
}