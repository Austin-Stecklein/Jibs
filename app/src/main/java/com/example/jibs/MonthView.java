package com.example.jibs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
    public int selectedDay;

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
                            selectedDay = dayOfMonth;
                        }
                    } else {
                        List<HolidayItem> emptyList = new ArrayList<>();
                        setList(emptyList);
                    }
                }
                //This is how we are handling the different months.
                else {
                    selectedDay = dayOfMonth;
                    callDataController(month);
                    currentMonth = month;
                }
            }
        });

        ListView listView = (ListView) findViewById(R.id.Box);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Id is the index of the list.
                sendMessage((int) id);
                //Log.i("Index", Long.toString(id));

            }
        });

        //This is how we are getting the correct month.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(calendarView.getDate());
        currentMonth = calendar.get(Calendar.MONTH) + 1;
        selectedDay = calendar.get(Calendar.DAY_OF_MONTH);

        //Starts a new thread to grab data for the month.
        DataController dataController = new DataControllerFactory().getController(this, currentMonth, false);
        Thread thread = new Thread((Runnable) dataController);
        thread.start();
    }

    //Used to send to the info page.
    public void sendMessage(int index) {
        Intent intent = new Intent(this, HolidayInfo.class);
        intent.putExtra("Name", holidays.get(selectedDay - 1 ).getHolidays().get(index).getName());
        intent.putExtra("Description", holidays.get(selectedDay - 1).getHolidays().get(index).getDescription());
        intent.putExtra("Date", holidays.get(selectedDay - 1).getHolidays().get(index).getDate());
        intent.putExtra("Notification", holidays.get(selectedDay - 1).getHolidays().get(index).getNotification());
        startActivity(intent);
    }

    //This is how we are still able to thread.
    public void callDataController(int month) {
        DataController dataController = new DataControllerFactory().getController(this, month, false);
        Thread thread = new Thread((Runnable) dataController);
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
            if (getHolidays().get(selectedDay - 1) != null) {
                setList(getHolidays().get(selectedDay - 1).getHolidays());
            }
        } else {
            List<HolidayItem> emptyList = new ArrayList<>();
            setList(emptyList);
        }
    }
    public List<HolidayContainer> getHolidays() { return holidays; }
}