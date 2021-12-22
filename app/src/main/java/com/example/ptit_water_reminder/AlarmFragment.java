package com.example.ptit_water_reminder;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.ptit_water_reminder.fragment.addAlarmFragment;
import com.example.ptit_water_reminder.helper.CustomLogListAdapter;
import com.example.ptit_water_reminder.helper.MyDatabaseHelper;
import com.example.ptit_water_reminder.models.Notification;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AlarmFragment extends Fragment {

    private ListView listView;
    private List<Notification> notification = new ArrayList<>();
    private CustomLogListAdapter logListAdapter;
    FloatingActionButton themAlarm;

    @TimeFormat private int clockFormat = TimeFormat.CLOCK_24H;
    private final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:00", Locale.getDefault());


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

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        MaterialTimePicker materialTimePicker = new MaterialTimePicker.Builder()
                .setTimeFormat(clockFormat)
                .setHour(hour)
                .setMinute(minute)
                .build();

        themAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Fragment addFragment = new addAlarmFragment();
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                transaction.add(R.id.fragment_container, addFragment);
//                transaction.addToBackStack(null);
//                transaction.commit();

                materialTimePicker.show(requireFragmentManager(), "fragment_tag");

                materialTimePicker.addOnPositiveButtonClickListener(dialog -> {
                    int newHour = materialTimePicker.getHour();
                    int newMinute = materialTimePicker.getMinute();
                    AlarmFragment.this.onTimeSet(newHour, newMinute);
                });
            }
        });

        return view;
    }

    private void onTimeSet(int newHour, int newMinute) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, newHour);
        cal.set(Calendar.MINUTE, newMinute);
        cal.setLenient(false);

        String format = formatter.format(cal.getTime());
        Log.i("TAG", format);
//        textView.setText(format);
        MyDatabaseHelper db = new MyDatabaseHelper(getActivity());
        db.addNotification(format);
    }
}