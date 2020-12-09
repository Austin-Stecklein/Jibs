package com.example.jibs;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;

public class AlertReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        HolidayNotification holidayNotification = new HolidayNotification(context);
        NotificationCompat.Builder nb = holidayNotification.getChannelNotification();
        holidayNotification.getManager().notify(1, nb.build());
    }
}
