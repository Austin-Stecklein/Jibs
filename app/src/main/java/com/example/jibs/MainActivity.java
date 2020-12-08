package com.example.jibs;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/*
This is the activity for the count down page.
 */
public class MainActivity extends AppCompatActivity {

    //This is a class that will hold all of the holidays to display on the count down
    //page
    List<HolidayItem> day;
    int month;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Calendar calendar = Calendar.getInstance();
        month = calendar.get(Calendar.MONTH) + 1;

        ListView listView = (ListView) findViewById(R.id.Box);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Id is the index of the list.
                sendMessage((int) id);

            }
        });



        DataController  dataController = new DataControllerFactory().getController(this, month, true);
        Thread thread = new Thread((Runnable) dataController);
        thread.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        DataController  dataController = new DataControllerFactory().getController(this, month, true);
        Thread thread = new Thread((Runnable) dataController);
        thread.start();
    }

    //Used to send to the info page.
    public void sendMessage(int index) {
        Intent intent = new Intent(this, HolidayInfo.class);
        intent.putExtra("Name", day.get(index).getName());
        intent.putExtra("Description", day.get(index).getDescription());
        intent.putExtra("Date", day.get(index).getDate());
        intent.putExtra("Notification", day.get(index).getNotification());
        startActivity(intent);
    }


    //This is used to set the list display
    public void setList(List<HolidayItem> day) {
        //if it is given an empty list it handles it perfectly.
        this.day = day;
        ArrayAdapter<HolidayItem> aa = new ArrayAdapter<HolidayItem>(this.getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, day);
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