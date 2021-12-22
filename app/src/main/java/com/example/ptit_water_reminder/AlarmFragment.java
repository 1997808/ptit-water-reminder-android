package com.example.ptit_water_reminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ptit_water_reminder.helper.CustomNotificationListAdapter;
import com.example.ptit_water_reminder.utils.AlarmReceiver;
import com.example.ptit_water_reminder.helper.MyDatabaseHelper;
import com.example.ptit_water_reminder.models.Notification;
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
    private List<Notification> notificationList = new ArrayList<>();
    private CustomNotificationListAdapter notificationListAdapter;
    FloatingActionButton themAlarm;
    private static final int MENU_ITEM_DELETE = 444;

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
        listView = view.findViewById(R.id.listNotificationView);
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
                materialTimePicker.show(requireFragmentManager(), "fragment_tag");

                materialTimePicker.addOnPositiveButtonClickListener(dialog -> {
                    int newHour = materialTimePicker.getHour();
                    int newMinute = materialTimePicker.getMinute();
                    AlarmFragment.this.onTimeSet(newHour, newMinute);
                });
            }
        });

        MyDatabaseHelper db = new MyDatabaseHelper(getActivity());
        List<Notification> data = db.getAllNotifications();
        notificationListAdapter = new CustomNotificationListAdapter(getActivity(), notificationList);
        listView.setAdapter(notificationListAdapter);
        notificationList.addAll(data);
        notificationListAdapter.notifyDataSetChanged();

        registerForContextMenu(this.listView);
        return view;
    }

    private void onTimeSet(int newHour, int newMinute) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, newHour);
        cal.set(Calendar.MINUTE, newMinute);
        cal.setLenient(false);

        String format = formatter.format(cal.getTime());
        Log.i("TAG", format);
        MyDatabaseHelper db = new MyDatabaseHelper(getActivity());
        if (!db.checkNotificationDuplicate(format)) {
            db.addNotification(format);
            List<Notification> data = db.getAllNotifications();
            this.notificationList.removeAll(notificationList);
            this.notificationList.addAll(data);
            this.notificationListAdapter.notifyDataSetChanged();
        }
        startAlarm(cal);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);
        menu.setHeaderTitle("Delete?");

        // groupId, itemId, order, title
        menu.add(0, MENU_ITEM_DELETE, 4, "Delete Notification");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo
                info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final Notification selectedNotification = (Notification) this.listView.getItemAtPosition(info.position);

        if (item.getItemId() == MENU_ITEM_DELETE) {
            MyDatabaseHelper db = new MyDatabaseHelper(getActivity());
            db.deleteNotification(selectedNotification);
            this.notificationList.remove(selectedNotification);
            // Refresh ListView.
            this.notificationListAdapter.notifyDataSetChanged();
        } else {
            return false;
        }
        return true;
    }

    private void startAlarm(Calendar c) {
        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getActivity(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), 1, intent, 0);

        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
    }
}