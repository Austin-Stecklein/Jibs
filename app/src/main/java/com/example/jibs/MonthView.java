package com.example.jibs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/*
This is the activity for the monthly view.
 */
public class MonthView extends AppCompatActivity {

    //This is a list of a list. Each index in this list represents a day.
    // i.e. index 1 is a list of holidays on the first of the month.
    private List<HolidayContainer> holidays = new ArrayList<>();
    private int currentMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_view);

        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);

        //This is a callback for when someone selects a new day.
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                month++; //This is to make the month the in the correct format.
                if(currentMonth == month) {
                    if (!getHolidays().isEmpty()) {
                        if (getHolidays().get(dayOfMonth - 1) != null) {
                            setList(getHolidays().get(dayOfMonth - 1).getHolidays());
                        }
                    } else {
                        List<HolidayItem> emptyList = new ArrayList<>();
                        setList(emptyList);
                    }
                }
                //This is how we are handling the different months.
                else {
                    callDataController(month);
                    currentMonth = month;
                }
            }
        });

        //This is how we are getting the correct month.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(calendarView.getDate());
        currentMonth = calendar.get(Calendar.MONTH) + 1;

        //Starts a new thread to grab data for the month.
        Thread thread = new Thread(new DataController(this, currentMonth));
        thread.start();
    }

    //This is how we are still able to thread.
    public void callDataController(int month) {
        Thread thread = new Thread(new DataController(this, month));
        thread.start();
    }


    //This is used to set the list display
    public void setList(List<HolidayItem> day) {
        //if it is given an empty list it handles it perfectly.
        ArrayAdapter<HolidayItem> aa = new ArrayAdapter<HolidayItem>(this.getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, day);
        ListView listView = (ListView) findViewById(R.id.Box);
        listView.setAdapter(aa);
    }

    //GETTERS AND SETTERS
    //The setHoliday now works for each different month.
    public void setHolidays(List<HolidayContainer> holidays, int month) {
        this.holidays = holidays;
        if (!holidays.isEmpty()) {
            if (getHolidays().get(month - 1) != null) {
                setList(getHolidays().get(month - 1).getHolidays());
            }
        } else {
            List<HolidayItem> emptyList = new ArrayList<>();
            setList(emptyList);
        }
    }
    public List<HolidayContainer> getHolidays() { return holidays; }
}