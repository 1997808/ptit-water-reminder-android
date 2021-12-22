package com.example.ptit_water_reminder.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.example.ptit_water_reminder.utils.AlarmReceiver;

import java.util.Calendar;
import java.util.Locale;

public class AlarmScheduler {
    private static int INDEX = 0;

    public static void create(Context context) {
        // Get AlarmManager instance
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        // Intent part
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.setAction("FOO_ACTION");
        intent.putExtra("KEY_FOO_STRING", "Medium AlarmManager Demo");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // 1
//        Calendar datetimeToAlarm = Calendar.getInstance(Locale.getDefault());
//        datetimeToAlarm.setTimeInMillis(System.currentTimeMillis());
//        datetimeToAlarm.set("HOUR_OF_DAY", reminderData.hour);
//        datetimeToAlarm.set("MINUTE", reminderData.minute);
//        datetimeToAlarm.set(Integer.parseInt("SECOND"), 0);
// 2
//        Calendar today = Calendar.getInstance(Locale.getDefault());
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
//                datetimeToAlarm.getTimeInMillis() , (long) 1000 * 60 * 60 * 24, pendingIntent);
//        return;
// 3
//        datetimeToAlarm.roll(Calendar.DAY_OF_WEEK, 1);
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
//                datetimeToAlarm.getTimeInMillis(), (long) 1000 * 60 * 60 * 24, pendingIntent);

        // Alarm time
//        int ALARM_DELAY_IN_SECOND = 1;
//        long alarmTimeAtUTC = System.currentTimeMillis() + ALARM_DELAY_IN_SECOND * 1_000L;

        // Set with system Alarm Service
        // Other possible functions: setExact() / setRepeating() / setWindow(), etc
//        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, alarmTimeAtUTC, pendingIntent);
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, alarmTimeAtUTC, (long) 1000 * 60 * 60 * 24, pendingIntent);
    }
}
