package com.example.ptit_water_reminder.utils;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.ptit_water_reminder.MainActivity;
import com.example.ptit_water_reminder.R;
import com.example.ptit_water_reminder.utils.MyApplication;

public class SchedulingService extends IntentService {
    private static final int TIME_VIBRATE = 1000;

    public SchedulingService() {
        super(SchedulingService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        int index = intent.getIntExtra("NOTIFICATION", 0);
        Intent notificationIntent = new Intent(this, MainActivity.class);
        notificationIntent
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        int requestID = (int) System.currentTimeMillis();
        PendingIntent pendingIntent = PendingIntent
                .getActivity(this, requestID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setContentTitle("Nhắc nhở uống nước")
                .setContentText("Hãy cập nhật lượng nước trong ngày")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(index, builder.build());
    }
}
