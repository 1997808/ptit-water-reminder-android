package com.example.ptit_water_reminder;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.ptit_water_reminder.fragment.addAlarmFragment;
import com.example.ptit_water_reminder.helper.CustomLogListAdapter;
import com.example.ptit_water_reminder.models.Notification;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class AlarmFragment extends Fragment {

    private ListView listView;
    private List<Notification> notification = new ArrayList<>();
    private CustomLogListAdapter logListAdapter;
    FloatingActionButton themAlarm;

    public AlarmFragment() {
        // Required empty public constructor
    }

    public static AlarmFragment newInstance() {
        AlarmFragment fragment = new AlarmFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Alarm");
        View view = inflater.inflate(R.layout.fragment_alarm, container, false);
        themAlarm = view.findViewById(R.id.themAlarm);
        themAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment addFragment = new addAlarmFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.fragment_container, addFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }
}