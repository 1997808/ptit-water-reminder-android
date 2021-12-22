package com.example.ptit_water_reminder;

import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ptit_water_reminder.fragment.addAlarmFragment;
import com.example.ptit_water_reminder.helper.CustomCupListAdapter;
import com.example.ptit_water_reminder.helper.CustomLogListAdapter;
import com.example.ptit_water_reminder.helper.CustomNotificationListAdapter;
import com.example.ptit_water_reminder.helper.MyDatabaseHelper;
import com.example.ptit_water_reminder.models.Cup;
import com.example.ptit_water_reminder.models.Notification;
import com.example.ptit_water_reminder.models.WaterLog;
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

        MyDatabaseHelper db = new MyDatabaseHelper(getActivity());
        List<Notification> data = db.getAllNotifications();
        notificationListAdapter = new CustomNotificationListAdapter(getActivity(), notificationList);
        listView.setAdapter(notificationListAdapter);
        notificationList.addAll(data);
        notificationListAdapter.notifyDataSetChanged();

        registerForContextMenu(this.listView);

//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
//                .setSmallIcon(R.drawable.notification_icon)
//                .setContentTitle(textTitle)
//                .setContentText(textContent)
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

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
        if (!db.checkNotificationDuplicate(format)) {
            db.addNotification(format);
            List<Notification> data = db.getAllNotifications();
//            notificationListAdapter = new CustomNotificationListAdapter(getActivity(), notificationList);
//            listView.setAdapter(notificationListAdapter);
//            registerForContextMenu(this.listView);
            this.notificationList.removeAll(notificationList);
            this.notificationList.addAll(data);
            this.notificationListAdapter.notifyDataSetChanged();
        }
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
}