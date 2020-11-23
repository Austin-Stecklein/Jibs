package com.example.jibs;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/*
This is the activity for the count down page.
 */
public class MainActivity extends AppCompatActivity {

    //This is a class that will hold all of the holidays to display on the count down
    //page

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //Starts a new thread to grab data for the month.
        //THIS WILL BE EXPANDED LATTER TO HANDLE DIFFERENT MONTHS.


        Thread thread = new Thread(new DataController(this));
        thread.start();
    }




    //This is used to set the list display
    public void setList(List<TempHoilday> day) {
        //if it is given an empty list it handles it perfectly.
        ArrayAdapter<TempHoilday> aa = new ArrayAdapter<TempHoilday>(this.getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, day);
        ListView listView = (ListView) findViewById(R.id.Box);

        listView.setAdapter(aa);
    }

    public void GotoInputPage(View view) {
        Intent intent = new Intent(this, InputScreen.class);
        startActivity(intent);
    }

    public void GotoCalendarPage(View view) {
        Intent intent = new Intent(this, MonthView.class);
        startActivity(intent);
    }


    //GETTERS AND SETTERS


}