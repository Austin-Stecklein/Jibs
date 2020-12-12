package com.example.jibs;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Calendar;

//This is the activity that is called when you click on an item in the list.
public class HolidayInfo extends AppCompatActivity {

    private HolidayItem holidayItem;
    public int day;
    public int month;
    public int year;
    public String daystil;
    public HolidayInfo holidayInfo = this;
    int iconId = R.drawable.add;
    private String name;
    private String date;
    private String description;
    private String notification;
    private String activity = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holiday_info);


        Intent intent = getIntent();
        name = intent.getStringExtra("Name");
        date = intent.getStringExtra("Date");
        description = intent.getStringExtra("Description");
        notification = intent.getStringExtra("Notification");
        iconId = intent.getIntExtra("location", R.drawable.add);
        activity = intent.getStringExtra("activity");

        ImageView imageView = (ImageView) findViewById(R.id.infoIcon);
        imageView.setImageResource(iconId);


        boolean notif = false;

        if(notification.equals("True")) {
            notif = true;
        }

        TextView textView = (TextView) findViewById(R.id.HolidayName);
        TextView textView1 = (TextView) findViewById(R.id.HolidayDate);
        TextView textView2 = (TextView) findViewById(R.id.HolidayDescription);
        Switch simpleSwitch = (Switch) findViewById(R.id.notfications);


        String[] dates = date.split("/");
        day = Integer.parseInt(dates[1]);
        month = Integer.parseInt(dates[0]);
        year = Integer.parseInt(dates[2]);

        Log.i("test1", Integer.toString(iconId));

        this.holidayItem = new HolidayItem(name, description, "", "", date, year, month, day, notification, Integer.toString(iconId));

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Date date1 = new Date(day, month, year);
                Calendar calendar = Calendar.getInstance();
                Date date2 = new Date(calendar.get(Calendar.DAY_OF_MONTH + 1), calendar.get(Calendar.MONTH + 1), calendar.get(Calendar.YEAR + 1));
                daystil = Integer.toString(date2.daysUntil(date1));
                holidayInfo.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView textView3 = (TextView) findViewById(R.id.CountDown);
                        String count = daystil + " Days til";
                        textView3.setText(count);
                    }
                });

            }
        });

        thread.start();



        textView.setText(name);
        textView1.setText(date);
        textView2.setText(description);
        simpleSwitch.setChecked(notif);


    }

    public void home(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goToGrid(View view) {
        Intent intent = new Intent(this, IconDisplay.class);
        intent.putExtra("activity", "info");
        intent.putExtra("Name", name);
        intent.putExtra("Description", description);
        intent.putExtra("Date", date);
        intent.putExtra("Notification", notification);
        intent.putExtra("location", iconId);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void startAlarm(Calendar c) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
    }

    //saveData allows the app to remember if
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void saveData(View view) {
        Switch simpleSwitch = (Switch) findViewById(R.id.notfications);
        boolean switchState = simpleSwitch.isChecked();
        SharedPreferences sharedPreferences = getSharedPreferences("shared_preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("hInfo_Notification" ,switchState);
        editor.putInt("hInfo_Year", holidayItem.date_year);
        editor.putInt("hInfo_Month", holidayItem.date_month);
        editor.putInt("hInfo_Day", holidayItem.date_day);
        editor.apply();

        String currentNot;
        if(switchState) {
            currentNot = "True";
            Calendar calendar = Calendar.getInstance();
            calendar.set(holidayItem.date_year, holidayItem.date_month, holidayItem.date_day);
            startAlarm(calendar);
        }
        else {
            currentNot = "False";
        }


        if(!holidayItem.notification.equals(currentNot) || activity.equals("IconDisplay")) {

            holidayItem.notification = currentNot;

            DataController dc = new DataControllerFactory().getController(this, holidayItem);
            dc.run();
        }
    }
}