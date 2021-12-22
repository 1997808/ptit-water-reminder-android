package com.example.ptit_water_reminder.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Date;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        MyApplication notificationHelper = new MyApplication(context);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
        notificationHelper.getManager().notify((int) new Date().getTime(), nb.build());
    }
}
