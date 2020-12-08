package com.example.jibs;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

//This is the activity that is called when you click on an item in the list.
public class HolidayInfo extends AppCompatActivity {

    private HolidayItem holidayItem;

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

        if(notification.equals("True")) {
            notif = true;
        }

        TextView textView = (TextView) findViewById(R.id.HolidayName);
        TextView textView1 = (TextView) findViewById(R.id.HolidayDate);
        TextView textView2 = (TextView) findViewById(R.id.HolidayDescription);
        Switch simpleSwitch = (Switch) findViewById(R.id.notfications);

        String[] dates = date.split("/");


        this.holidayItem = new HolidayItem(name, description, "", "", date, Integer.parseInt(dates[2]), Integer.parseInt(dates[0])
               , Integer.parseInt(dates[1]), notification, "");


        textView.setText(name);
        textView1.setText(date);
        textView2.setText(description);
        simpleSwitch.setChecked(notif);


    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void saveData(View view) {
        Switch simpleSwitch = (Switch) findViewById(R.id.notfications);
        boolean switchState = simpleSwitch.isChecked();


        String currentNot;
        if(switchState) {
            currentNot = "True";
        }
        else {
            currentNot = "False";
        }


        if(!holidayItem.notification.equals(currentNot)) {

            holidayItem.notification = currentNot;

            DataController dc = new DataControllerFactory().getController(this, holidayItem);
            dc.run();
        }
    }
}