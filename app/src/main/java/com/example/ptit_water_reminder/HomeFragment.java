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
import com.example.ptit_water_reminder.fragment.EditUserWaterTargetFragment;
import com.example.ptit_water_reminder.helper.MyDatabaseHelper;
import com.example.ptit_water_reminder.models.User;
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
        TextView daily_target_amount = view.findViewById(R.id.daily_target_amount);
        CircularProgressIndicator progress = view.findViewById(R.id.progress_circular_waterlog);
        FloatingActionButton floatingButtonHome = view.findViewById(R.id.floating_button_home);
        getActivity().setTitle("Home");

        daily_target_amount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, EditUserWaterTargetFragment.newInstance("", ""));
                transaction.addToBackStack(null);
                transaction.commit();
                getActivity().setTitle("Edit Water Target");
            }
        });

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
        User user = db.getUser();
        int sum = db.getSumWaterLogToday();
        int percentInt = calculatePercentage(sum, user.getWaterTarget());
        progress.setProgressCompat(percentInt, false);
        percent.setText(percentInt + "%");
        overview_ml.setText(sum + " ml");
        daily_target_amount.setText("Daily target " + user.getWaterTarget() + " ml");
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