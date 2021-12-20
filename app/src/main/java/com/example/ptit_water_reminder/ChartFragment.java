package com.example.ptit_water_reminder;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class ChartFragment extends Fragment {

    BarChart bar;
    public ChartFragment() {
    }

    public static ChartFragment newInstance() {
        ChartFragment fragment = new ChartFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Chart");

        View view = inflater.inflate(R.layout.fragment_history, container, false);

        BarChart chart = view.findViewById(R.id.barchart);

        ArrayList<BarEntry> visitors= new ArrayList<>();


        visitors.add(new BarEntry(2014, 0));
        visitors.add(new BarEntry(2015, 1));
        visitors.add(new BarEntry(2016, 2));
        visitors.add(new BarEntry(2017, 3));
        visitors.add(new BarEntry(2018, 4));
        visitors.add(new BarEntry(2019, 5));
        visitors.add(new BarEntry(2020, 6));



        BarDataSet bardataset = new BarDataSet(visitors, " du lieu");
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        bardataset.setValueTextColor(Color.BLACK);
        bardataset.setValueTextSize(16f);

        BarData barData = new BarData(bardataset);
        chart.setFitBars(true);
        chart.setData(barData);
        chart.getDescription().setText("example");
        chart.animateY(10);

        return view;
    }



    }
