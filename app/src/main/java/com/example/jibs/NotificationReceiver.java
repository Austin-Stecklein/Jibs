package com.example.jibs;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.util.Calendar;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onReceive(Context context, Intent intent) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("shared_preferences", Context.MODE_PRIVATE);
        boolean x = sharedPreferences.getBoolean("hInfo_Notification", false);
        int hInfo_Year = sharedPreferences.getInt("hInfo_Year", 0);
        int hInfo_Month = sharedPreferences.getInt("hInfo_Month", 0);
        int hInfo_Day = sharedPreferences.getInt("hInfo_Day", 0);
        Log.d("boot", "Boot Completed");;
        if(x) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(hInfo_Year, hInfo_Month, hInfo_Day);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            Intent intent1 = new Intent(context, AlertReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1, intent1, 0);
            if (calendar.before(Calendar.getInstance())) {
                calendar.add(Calendar.DATE, 1);
            }
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        }
    }
}