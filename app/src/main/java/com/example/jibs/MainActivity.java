package com.example.jibs;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {
//trying to get the repository to work
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LocalDate date = LocalDate.now();
        LocalDate date1 = date.withMonth(1);
        DayOfWeek week = date1.getDayOfWeek();
        Log.i("My Tag", String.valueOf(date1));
        Log.i("Day of week", String.valueOf(week));

        TextView textView = (TextView)findViewById(R.id.Date);
        textView.setText(String.valueOf(week));

    }
}