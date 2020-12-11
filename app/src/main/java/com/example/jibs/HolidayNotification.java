package com.example.jibs;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.view.View;
import android.widget.TextView;

import androidx.core.app.NotificationCompat;

public class HolidayNotification extends ContextWrapper {
    public static final String channelID = "channelID";
    public static final String channelName = "Channel Name";
    private NotificationManager mManager;
    public HolidayNotification(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }
    }
    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);
        getManager().createNotificationChannel(channel);
    }
    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }
    public NotificationCompat.Builder getChannelNotification(String string) {
        //Set the title of the notification and the description. ie. "Christmas" and "Hope it snows!"
        //TextView holidayName = (TextView) findViewById(R.id.HolidayName);
        return new NotificationCompat.Builder(getApplicationContext(), channelID)
            //.setContentTitle(holidayName.getText().toString())
            .setContentTitle("Holiday coming up!")
            .setContentText("It's almost time for the holiday!");
    }
}
