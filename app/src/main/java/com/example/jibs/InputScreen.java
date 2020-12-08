package com.example.jibs;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

/*
This is the activity where user can enter in their own holidays.
 */
public class InputScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_screen);
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
        }
        else {
            notifications = "False";
        }

        String date = Integer.toString(month) + "/" + Integer.toString(day) + "/" + Integer.toString(year);

        HolidayItem holidayItem = new HolidayItem(name, description, "", "", date ,year, month, day, notifications, "");
        int confirm = new UserSaveData(this).saveData(holidayItem);
        if(confirm == 0) {
            new Confirmation().onReceive(this, "Added Holiday");

        }
        else {
            new Confirmation().onReceive(this, "failed");
        }
    }

}