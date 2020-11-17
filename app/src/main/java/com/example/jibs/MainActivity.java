package com.example.jibs;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    //This is a list of a list. Each index in this list represents a day.
    // i.e. index 1 is a list of holidays on the first of the month.
    private List<HolidayContainer> holidays = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);

        //This is a callback for when someone selects a new day.
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                if(!getHolidays().isEmpty()) {
                    if(getHolidays().get(dayOfMonth - 1) != null) {
                        setList(getHolidays().get(dayOfMonth - 1).getHolidays());
                    }
                }
                else {
                    List<TempHoilday> emptyList = new ArrayList<>();
                    setList(emptyList);
                }
            }
        });

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

    //GETTERS AND SETTERS
    public void setHolidays(List<HolidayContainer> holidays) { this.holidays = holidays; }
    public List<HolidayContainer> getHolidays() { return holidays; }
}