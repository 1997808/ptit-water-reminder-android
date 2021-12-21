package com.example.ptit_water_reminder;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ptit_water_reminder.helper.MyDatabaseHelper;
import com.example.ptit_water_reminder.models.WaterLog;
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
        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        BarChart chart = view.findViewById(R.id.barchart);
        ArrayList<BarEntry> luongnuoc = new ArrayList<>();
        ArrayList<String> xAxisLabel = new ArrayList<>();

        MyDatabaseHelper db = new MyDatabaseHelper(getActivity());
        List<WaterLog> data = db.getWaterLogChart();

        for (int i = 0; i < data.size(); i++) {
            xAxisLabel.add(data.get(i).getCreateAt());
            luongnuoc.add(new BarEntry(i, data.get(i).getAmount()));
        }

        BarDataSet bardataset = new BarDataSet(luongnuoc, "Lượng nước trong các ngày");
        bardataset.setColors(Color.parseColor("#00B1FF"));
//        bardataset.setValueTextColor(Color.BLACK);
        bardataset.setValueTextSize(14f);

        BarData barData = new BarData(bardataset);
        chart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLabel));
        chart.getXAxis().setDrawGridLines(false);
        // start at 0
        chart.getAxisLeft().setAxisMinimum(0f);
        chart.getAxisRight().setDrawLabels(false);
        chart.getAxisRight().setDrawGridLines(false);
        chart.getLegend().setEnabled(false);
        chart.setFitBars(false);
        chart.setData(barData);
        chart.getDescription().setText("");
        chart.animateY(1000);

        return view;
    }


}
