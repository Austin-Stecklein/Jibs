package com.example.jibs;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;

import java.util.Calendar;

/*
This is the activity where user can enter in their own holidays.
 */
public class InputScreen extends AppCompatActivity {

    int iconId = R.drawable.add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_screen);
        if(this.getCallingActivity() == new IconDisplay().getComponentName()) {
            Intent intent = getIntent();
            iconId = intent.getIntExtra("location", R.drawable.add);
        }

        ImageView imageView = (ImageView) findViewById(R.id.InputIcon);
        imageView.setImageResource(iconId);
    }

    public void goToGrid(View view) {
        Intent intent = new Intent(this, IconDisplay.class);
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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void addData(View view) {
        EditText editText = (EditText) findViewById(R.id.Name);
        EditText editText1 = (EditText) findViewById(R.id.Description);
        EditText editText2 = (EditText) findViewById(R.id.month);
        EditText editText3 = (EditText) findViewById(R.id.day);
        EditText editText4 = (EditText) findViewById(R.id.year);

        Switch simpleSwitch = (Switch) findViewById(R.id.enable);

        //This is the data from the page.
        String name = editText.getText().toString();
        String description = editText1.getText().toString();

        int month = 0;


        if(!"".equals(editText2.toString())) {
            month = Integer.parseInt(editText2.getText().toString());
        }
        int day= 0;
        if(!"".equals(editText3.toString())) {
            day = Integer.parseInt(editText3.getText().toString());
        }
        int year = 0;
        if(!"".equals(editText4.toString())) {
            year = Integer.parseInt(editText4.getText().toString());
        }
        boolean switchState = simpleSwitch.isChecked();

        String notifications;
        if(switchState) {
            notifications = "True";
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);
            startAlarm(calendar);
        }
        else {
            notifications = "False";
        }

        String date = Integer.toString(month) + "/" + Integer.toString(day) + "/" + Integer.toString(year);

        HolidayItem holidayItem = new HolidayItem(name, description, "", "", date ,year, month, day, notifications, iconId);
        int confirm = new UserSaveData(this).saveData(holidayItem);
        if(confirm == 0) {
            new Confirmation().onReceive(this, "Added Holiday");

        }
        else {
            new Confirmation().onReceive(this, "failed");
        }
    }

}