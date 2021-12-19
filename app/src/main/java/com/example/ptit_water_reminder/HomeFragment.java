package com.example.ptit_water_reminder;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ptit_water_reminder.helper.MyDatabaseHelper;
import com.google.android.material.progressindicator.CircularProgressIndicator;

import org.w3c.dom.Text;

public class HomeFragment extends Fragment {
    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        TextView percent = view.findViewById(R.id.overview_percentage);
        MyDatabaseHelper db = new MyDatabaseHelper(getActivity());
        int sum = db.getSumWaterLogToday();
        Log.i("TAG", String.valueOf(sum));
        CircularProgressIndicator progress = view.findViewById(R.id.progress_circular_waterlog);
        progress.setProgressCompat(30, true);
        percent.setText(30 + "%");
        return view;
    }
}