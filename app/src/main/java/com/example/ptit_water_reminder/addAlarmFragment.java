package com.example.ptit_water_reminder;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.ptit_water_reminder.helper.CustomLogListAdapter;
import com.example.ptit_water_reminder.helper.MyDatabaseHelper;
import com.example.ptit_water_reminder.models.Notification;
import com.example.ptit_water_reminder.models.WaterLog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class addAlarmFragment extends Fragment {
    private TimePicker timePicker;
    private boolean is24HView = true;

    Button btChon,btTat;
    TextView txtHienThi;
    Calendar calendar;




    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public addAlarmFragment() {
        // Required empty public constructor
    }


    public static addAlarmFragment newInstance(String param1, String param2) {
        addAlarmFragment fragment = new addAlarmFragment();
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
        View view = inflater.inflate(R.layout.fragment_add_alarm, container, false);

        btChon= view.findViewById(R.id.buttonChon);
        btTat= view.findViewById(R.id.buttonTat);
        txtHienThi= view.findViewById(R.id.textViewHienThiGio);
        calendar= Calendar.getInstance();

        this.timePicker = (TimePicker) view.findViewById(R.id.timePicker);
        this.timePicker.setIs24HourView(this.is24HView);

        btChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
                calendar.set(Calendar.MINUTE, timePicker.getMinute());

                int gio= timePicker.getHour();
                int phut=timePicker.getMinute();

                String string_gio=String.valueOf(gio);
                String string_phut=String.valueOf(phut);


                txtHienThi.setText("ban dat "+string_gio+" : "+string_phut);
            }
        });

        this.timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

            }
        });

        return view;
    }


}

