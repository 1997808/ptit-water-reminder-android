package com.example.ptit_water_reminder.utils;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.example.ptit_water_reminder.R;

public class MyApplication extends ContextWrapper {
    public static final String CHANNEL_ID = "CHANNEL_1";
    private NotificationManager mManager;

    public MyApplication(Context base) {
        super(base);
        createNotificationChannel();
    }

//    @Override
//    public void onCreate() {
//        super.onCreate();
//        createNotificationChannel();
//    }

    //setup notification channel
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }

    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return mManager;
    }

    public NotificationCompat.Builder getChannelNotification() {
        return new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setContentTitle("Nhắc nhở uống nước!")
                .setContentText("Hãy cập nhật lượng nước trong ngày")
                .setSmallIcon(R.drawable.ic_notifications_black_24dp);
    }
}
