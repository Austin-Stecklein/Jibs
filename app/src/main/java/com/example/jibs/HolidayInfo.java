package com.example.jibs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Switch;
import android.widget.TextView;

//This is the activity that is called when you click on an item in the list.
public class HolidayInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holiday_info);

        Intent intent = getIntent();
        String name = intent.getStringExtra("Name");
        String date = intent.getStringExtra("Date");
        String description = intent.getStringExtra("Description");
        String notification = intent.getStringExtra("Notification");
        boolean notif = false;

        if(notification == "true") {
            notif = true;
        }

        TextView textView = (TextView) findViewById(R.id.HolidayName);
        TextView textView1 = (TextView) findViewById(R.id.HolidayDate);
        TextView textView2 = (TextView) findViewById(R.id.HolidayDescription);
        Switch simpleSwitch = (Switch) findViewById(R.id.notfications);

        textView.setText(name);
        textView1.setText(date);
        textView2.setText(description);
        simpleSwitch.setChecked(notif);
    }
}