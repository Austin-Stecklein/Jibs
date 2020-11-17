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

    private List<HolidayContainer> holidays = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                if(!getHolidays().isEmpty()) {
                    if(getHolidays().get(dayOfMonth - 1) != null) {
                        setList(getHolidays().get(dayOfMonth - 1).getHolidays());
                    }
                }
            }
        });
        Thread thread = new Thread(new DataController(this));
        thread.start();
    }

    public void setList(List<TempHoilday> day) {
        if(!day.isEmpty()) {
            ArrayAdapter<TempHoilday> aa = new ArrayAdapter<TempHoilday>(this.getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, day);
            ListView listView = (ListView) findViewById(R.id.Box);

            listView.setAdapter(aa);
        }
    }

    public void setHolidays(List<HolidayContainer> holidays) {
        this.holidays = holidays;
    }

    public List<HolidayContainer> getHolidays() {
        return holidays;
    }
}