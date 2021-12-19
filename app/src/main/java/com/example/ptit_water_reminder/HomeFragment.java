package com.example.ptit_water_reminder;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ptit_water_reminder.fragment.AddLogHomeFragment;
import com.example.ptit_water_reminder.helper.MyDatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
        TextView overview_ml = view.findViewById(R.id.overview_ml);
        CircularProgressIndicator progress = view.findViewById(R.id.progress_circular_waterlog);
        FloatingActionButton floatingButtonHome = view.findViewById(R.id.floating_button_home);
        getActivity().setTitle("Home");

        floatingButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, AddLogHomeFragment.newInstance("", ""));
                transaction.addToBackStack(null);
                transaction.commit();
                getActivity().setTitle("Add Log");
            }
        });

        MyDatabaseHelper db = new MyDatabaseHelper(getActivity());
        int sum = db.getSumWaterLogToday();
        Log.i("TAG", String.valueOf(sum));
        int percentInt = calculatePercentage(sum, 2000);
        progress.setProgressCompat(percentInt, false);
        percent.setText(percentInt + "%");
        overview_ml.setText(sum + " ml");
        return view;
    }

    private int calculatePercentage(int n, int max) {
        int result = 0;
        if (n > max) {
            result = 100;
        } else {
            result = n * 100 / max;
        }
        return (int) Math.floor(result);
    }
}